package com.example.demo.controller;

import java.net.Authenticator.RequestorType;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.Grades;
import com.example.demo.entities.Students;
import com.example.demo.entities.Subjects;
import com.example.demo.entities.Teachers;
import com.example.demo.security.StudentDetails;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/student/**")
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/login")
	public String studentLogin() {
		return "StudentLoginView";
	}
	@GetMapping("/mainview")
	public String studentMainView(@AuthenticationPrincipal StudentDetails studentDetails , HttpSession session) {
		session.setAttribute("studentid", studentDetails.getId());
		session.setAttribute("teacherid", studentDetails.getTeacherId());
		return "StudentMainView";
	}
	@GetMapping("/view-subject")
	public String studentSubjectView(Model model , HttpSession session) {
		
		Students students = studentService.findStudentById((long)session.getAttribute("studentid"));
		
		List<Subjects> subjects = studentService.findStudentSubjectById((long) session.getAttribute("studentid"));
		
		model.addAttribute("subjects",subjects);
		model.addAttribute("students" ,students);
		return "StudentSubjectView";
	}
	@GetMapping("/view-grades")
	public String studentGradesView(@RequestParam("id") long subjectId , Model model) {
		
		Subjects subjects = studentService.findSubjectById(subjectId);
		
		Grades grades = studentService.findStudentGradesById(subjectId);
		model.addAttribute("grades",grades);
		model.addAttribute("subjects",subjects);
		
		return "StudentGradesView";
	}
	@GetMapping("/view-adviser")
	public String adviserProfileView(Model model , HttpSession session) {
		
		Teachers teachers = studentService.findTeacherByStudentId((long) session.getAttribute("teacherid"));
		
		model.addAttribute("teachers",teachers);
		
		
		return "StudentAdviserProfileView";
	}
	@GetMapping("/update-profile")
	public String updateProfile(Model model , HttpSession session) {
		
		Students students = studentService.findStudentById((long) session.getAttribute("studentid"));
		
		model.addAttribute("students",students);
		
		return "StudentUpdateProfile";
	}
	@RequestMapping(value = "/update-profile" ,method = {RequestMethod.PUT , RequestMethod.POST})
	public String processUpdateProfile(@Valid Students students , BindingResult bindingResult , RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			return "StudentUpdateProfile";
		}
		studentService.updateStudents(students);
		redirectAttributes.addFlashAttribute("message" , "Successfully updated a profile");
		return "redirect:/student/update-profile";
		
	}
	@GetMapping("/password-reset")
	public String passwordResetView(Model model , HttpSession session) {
		
		Students students = studentService.findStudentById((long)session.getAttribute("studentid"));
		students.setPassword(null);
		model.addAttribute("students",students);
		
		return "StudentResetPasswordView";
	}
	@RequestMapping(value="/password-reset" , method = {RequestMethod.PUT , RequestMethod.POST})
	public String passwordResetProcess(@Valid Students students , BindingResult bindingResult,HttpSession session , RedirectAttributes redirectAttributes) {
				
		
		if(bindingResult.hasErrors()) {
			System.out.println("ERror" +bindingResult.getErrorCount());
			return "StudentResetPasswordView";
		}		
		Students findStudentById = studentService.findStudentById((long)session.getAttribute("studentid"));
		
		findStudentById.setPassword(students.getPassword());
		studentService.saveResetPassword(findStudentById);
		
		redirectAttributes.addFlashAttribute("message" ,"Successfully reset a password");
	
		return "redirect:/student/password-reset";
		
	}
	
	
	
	
	
	
	
	
	

}
