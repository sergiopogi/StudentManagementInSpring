package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.Students;
import com.example.demo.entities.Teachers;
import com.example.demo.service.AdminService;

@Controller
@RequestMapping("/admin/**")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/login")
	public String adminLoginView() {
		
		return "AdminLoginView";
	}
	@GetMapping("/mainview")
	public String adminMainView() {	
		
		return "AdminMainView";	
	}
	@GetMapping("/add-teacher")
	public String addTeacherView(@ModelAttribute("teachers") Teachers teachers) {	
		return "AdminAddTeacherView";
	}
	
	@PostMapping("/add-teacher")
	public String addTeacherProcess(@Valid Teachers teachers , BindingResult bindingResult , RedirectAttributes redirectAttributes) {
			
		if(bindingResult.hasErrors()) {
			return "AdminAddTeacherView";
		}
		
		adminService.addTeacher(teachers);
		redirectAttributes.addFlashAttribute("message","Successfully added new teacher!");
		return "redirect:/admin/add-teacher";
	}
	@GetMapping("/view-teachers")
	public String adminTeachersView(Model model) {
		
		List<Teachers> findAllTeachers = adminService.findAllTeachers();
		model.addAttribute("teachers" ,findAllTeachers);
		
		return "AdminTeachersView";
	}
	@GetMapping("/view-students")
	public String adminStudentsView(Model model) {
		
		List<Students> students = adminService.findAllStudents();
		model.addAttribute("students" ,students);
		
		return "AdminStudentsView";
	}
	@RequestMapping(value = "/delete-teacher" , method = {RequestMethod.GET , RequestMethod.DELETE})
	public String deleteTeacher(@RequestParam("id") long teacherId , RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("message","Successfully deleted teacher !");
		
		adminService.deleteTeacherById(teacherId);
		return "redirect:/admin/view-teachers";
	}
	@GetMapping("/update-teacher")
	public String updateTeacherView(@RequestParam("id") long teacherId , Model model) {
		
		Teachers teachers = adminService.findTeacherById(teacherId);
		model.addAttribute("teachers",teachers);
		
		return "AdminTeacherUpdateView";
	}
	
	@RequestMapping(value = "/update-teacher" , method = {RequestMethod.PUT , RequestMethod.POST})
	public String updateTeacherProcess(@Valid Teachers teachers , BindingResult bindingResult ,RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return "AdminTeacherUpdateView";
		}
		redirectAttributes.addFlashAttribute("message" ,"Successfully updated !");
		adminService.saveUpdateTeacher(teachers);
		return "redirect:/admin/view-teachers";
	}
	@GetMapping("/reset-password")
	public String resetTeacherPasswordView(@RequestParam("id") long teacherId , Model model) {
		
		Teachers teachers = adminService.findTeacherById(teacherId);
		model.addAttribute("username",teachers.getUsername());
		model.addAttribute("teachers",teachers);
		
		System.out.println("Teacher id is "+teacherId);
		
		return "AdminTeacherResetPasswordView";
	}
	@RequestMapping(value = "/reset-password" , method = {RequestMethod.PUT , RequestMethod.POST})
	public String resetTeacherPasswordProcess(@Valid Teachers teachers , BindingResult bindingResult , RedirectAttributes redirectAttributes , Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("username",teachers.getUsername());
			return "AdminTeacherResetPasswordView";
			
		}
		
		adminService.saveResetPassword(teachers);
		redirectAttributes.addFlashAttribute("message" ,"Password reset successful");
		
		return "redirect:/admin/view-teachers";
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}
