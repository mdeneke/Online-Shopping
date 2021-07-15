package edu.miu.cs.cs425.webapps.elibrary.controller;

import edu.miu.cs.cs425.webapps.elibrary.model.Publisher;
import edu.miu.cs.cs425.webapps.elibrary.service.PublisherService;
import edu.miu.cs.cs425.webapps.elibrary.service.impl.PublisherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/elibrary/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;


    @GetMapping(value = "/list")
    public ModelAndView displayListOfPublishers() {
        ModelAndView modelAndView = new ModelAndView();
        List<Publisher> publishers = publisherService.getAllPublishers();
        modelAndView.addObject("publisher", publishers);
        modelAndView.setViewName("publisher/list");
        return modelAndView;
    }

    @GetMapping(value = "/new")
    public String newPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/new";
    }

    @PostMapping(value = "/new")
    public String addNewpublisher(@Valid @ModelAttribute("supplier") Publisher publisher,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "publisher/new";
        }
        publisher = publisherService.addNewPublisher(publisher);
        return "redirect:/elibrary/publisher/list";
    }

}
