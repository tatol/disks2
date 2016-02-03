package disks2.controller;

import disks2.domain.Disk;
import disks2.domain.User;
import disks2.domain.UserRole;
import disks2.service.DiskService;

import javax.servlet.http.HttpServletRequest;

import disks2.service.UserRoleService;
import disks2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.*;

/**
 * Created by adminvl on 21.10.2015.
 */
@RestController
public class DiskController {
    @Autowired
    private DiskService diskService;

    @Autowired
    private UserService userService;

    Integer getCurrentUserId(Principal principal)
    {
        User user = userService.getUserByName( principal.getName());
        return user.getId();
    }

    @RequestMapping(value = "/listOwnDisks")
    public ResponseEntity<List<Disk>> listOwnDisks(Principal principal) {
        List<Disk> disks = diskService.listOwnDisks(getCurrentUserId(principal));
        return new ResponseEntity<List<Disk>>(disks,HttpStatus.OK);
    }

    @RequestMapping(value = "/listFreeDisks")
    public ResponseEntity<List<Disk>> listFreeDisks(Principal principal) {
        List<Disk> disks = diskService.listFreeDisks();
        return new ResponseEntity<List<Disk>>(disks,HttpStatus.OK);
    }

    @RequestMapping(value = "/listOwnDisksFromAllUsers")
    public ResponseEntity<List<Disk>> listOwnDisksFromAllUsers(Principal principal) {
        List<Disk> disks = diskService.listOwnDisksFromAllUsers(getCurrentUserId(principal));
        return new ResponseEntity<List<Disk>>(disks,HttpStatus.OK);
    }

    @RequestMapping(value = "/listTakenDisksByUser")
    public ResponseEntity<List<Disk>> listTakenDisksByUser(Principal principal) {
        List<Disk> disks = diskService.listTakenDisksByUser(getCurrentUserId(principal));
        return new ResponseEntity<List<Disk>>(disks,HttpStatus.OK);
    }

    @RequestMapping(value = "/listTakenDisksFromUser")
    public ResponseEntity<List<Disk>> listTakenDisksFromUser(Principal principal) {
        List<Disk> disks = diskService.listTakenDisksFromUser(getCurrentUserId(principal));
        return new ResponseEntity<List<Disk>>(disks,HttpStatus.OK);
    }

    @RequestMapping(value="/takeFreeDisk/{id}",method = RequestMethod.PUT)
    public void takeFreeDisk(@PathVariable Integer id, Principal principal)
    {
        diskService.takeFreeDisk(getCurrentUserId(principal), id);
    }
    @RequestMapping(value="/takeFreeDiskFromUser/{diskId}/{fromId}",method = RequestMethod.PUT)
    public void takeFreeDiskFromUser(@PathVariable Integer diskId,@PathVariable Integer fromId, Principal principal)
    {
        diskService.takeFreeDiskFromUser(getCurrentUserId(principal),diskId,fromId);
    }
    @RequestMapping(value="returnDiskToUser/{diskId}/{fromId}",method = RequestMethod.PUT)
    public void returnDiskToUser(@PathVariable Integer diskId,@PathVariable Integer fromId, Principal principal)
    {
        diskService.returnDiskToUser(getCurrentUserId(principal),diskId,fromId);
    }
    @RequestMapping(value="returnOwnDisk/{id}/",method = RequestMethod.PUT)
    public void returnOwnDisk(@PathVariable Integer id, Principal principal)
    {
        diskService.returnOwnDisk(getCurrentUserId(principal), id);
    }

}
