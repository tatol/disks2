package disks2.controller;

import disks2.domain.Disk;
import disks2.domain.User;
import disks2.domain.UserRole;
import disks2.service.DiskService;

import javax.servlet.http.HttpServletRequest;

import disks2.service.UserRoleService;
import disks2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.*;

/**
 * Created by adminvl on 21.10.2015.
 */
@RestController
public class DiskController {
    @Autowired
    private DiskService diskService;

    private User getCurrentUser() {
        User user = new User();
        user.setId(1);
            return user;
    }

    @RequestMapping(value = "/listOwnDisks")
    public ResponseEntity<List<Disk>> listOwnDisks() {
        List<Disk> disks = diskService.listOwnDisks(getCurrentUser().getId());

        return new ResponseEntity<List<Disk>>(disks,HttpStatus.OK);
    }

    @RequestMapping(value = "/listFreeDisks")
    public ModelAndView listFreeDisks() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listFreeDisks", diskService.listFreeDisks());
            model.put("listOwnDisksFromAllUsers", diskService.listOwnDisksFromAllUsers(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listFreeDisks", model);
    }
    @RequestMapping(value = "/listTakenDisksByUser")
    public ModelAndView listTakenDisksByUser() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listTakenDisksByUser", diskService.listTakenDisksByUser(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listTakenDisksByUser", model);
    }
    @RequestMapping(value = "/listTakenDisksFromUser")
    public ModelAndView listTakenDisksFromUser() {
        Map<String, Object> model = new HashMap<String, Object>();
        try
        {
            model.put("listTakenDisksFromUser", diskService.listTakenDisksFromUser(getCurrentUser().getId()));
        }
        catch (NullPointerException e)
        {
            return new ModelAndView("error");
        }
        return new ModelAndView("listTakenDisksFromUser", model);
    }
    @RequestMapping(value="/{id}/takeFreeDisk")
    public ModelAndView takeFreeDisk(@PathVariable Integer id)
    {
        diskService.takeFreeDisk(getCurrentUser().getId(), id);
        return new ModelAndView("redirect:/listFreeDisks");
    }
    @RequestMapping(value="/{diskId}/{fromId}/takeFreeDiskFromUser")
    public ModelAndView takeFreeDiskFromUser(@PathVariable Integer diskId,@PathVariable Integer fromId)
    {
        diskService.takeFreeDiskFromUser(getCurrentUser().getId(), diskId, fromId);
        return new ModelAndView("redirect:/listFreeDisks");
    }
    @RequestMapping(value="/{diskId}/{fromId}/returnDiskToUser")
    public ModelAndView returnDiskToUser(@PathVariable Integer diskId,@PathVariable Integer fromId)
    {
        diskService.returnDiskToUser(getCurrentUser().getId(), diskId, fromId);
        return new ModelAndView("redirect:/listTakenDisksByUser");
    }
    @RequestMapping(value="/{id}/returnOwnDisk")
    public ModelAndView returnOwnDisk(@PathVariable Integer id)
    {
        diskService.returnOwnDisk(getCurrentUser().getId(), id);
        return new ModelAndView("redirect:/listOwnDisks");
    }

}
