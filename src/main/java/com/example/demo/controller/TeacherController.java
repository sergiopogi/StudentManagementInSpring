package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.example.demo.entities.Grades;
import com.example.demo.entities.Students;
import com.example.demo.entities.Subjects;
import com.example.demo.entities.Teachers;
import com.example.demo.security.TeacherDetails;
import com.example.demo.service.GradesService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.TeacherService;

@Controller
@RequestMapping("/teacher/**")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private GradesService gradeService;
	
	@GetMapping("/login")
	public String teacherLoginView() {	
		
		return "TeacherLoginView";
	}
	@GetMapping("/mainview")
	public String teacherMainView(@AuthenticationPrincipal TeacherDetails teacherDetails , HttpSession session) {
		
		session.setAttribute("teacherid", teacherDetails.getId());
				
		return "TeacherMainView";
	}
	
	
	@GetMapping("/signup-student")
	public String studentSignup(Students students ,Model model) {		
		model.addAttribute("students",students);
		return "TeacherAddStudent";
	}
	
	@PostMapping("/signup-student")
	public String studentS(@Valid Students students ,BindingResult result , HttpSession session , RedirectAttributes redirectAttributes) {	
		if(result.hasErrors()) {
			return "TeacherAddStudent";
		}
		teacherService.addStudent(students, (long)session.getAttribute("teacherid"));
		redirectAttributes.addFlashAttribute("message" , "Successfully added new student !");
		return "redirect:/teacher/signup-student";
	}
	
	
	
	@GetMapping("/view-students")
	public String viewStudents(Model model , HttpSession session) {
		List<Students> findStudents = teacherService.findStudentsByTeacherId((long) session.getAttribute("teacherid"));	
		model.addAttribute("studentList" ,findStudents);
		return "TeacherStudentListView";
	}
	@RequestMapping(value = "/delete-student" ,method = {RequestMethod.DELETE , RequestMethod.GET})
	public String deleteStudent(@RequestParam("id") long studentId) {
		teacherService.deleteStudentById(studentId);
		return "redirect:/teacher/view-students";
		
	}
		
	@GetMapping("/update-student")
	public String updateStudentView(@RequestParam("id") long studentId , Model model , HttpSession session) {
	
		Students students = teacherService.findStudent(studentId);
		model.addAttribute("students",students);
		return "TeacherUpdateStudentView";
	}

	
	@RequestMapping(value = "/update-student" , method = {RequestMethod.PUT ,RequestMethod.POST})
	public String updateStudent(@Valid Students students ,BindingResult bindingResult , RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("Error is "+bindingResult.getFieldError());
			return "TeacherUpdateStudentView";
		}
		
		teacherService.updateStudent(students);
		redirectAttributes.addFlashAttribute("message" , "Successfully updated a student !");
		return "redirect:/teacher/view-students" ;
	}
	
	@GetMapping("/reset-password")
	public String resetStudentPassword(@RequestParam("id") long studentId ,Model model) {
		
		Students students = teacherService.findStudent(studentId);
		students.setPassword(null);
		model.addAttribute("username",students.getUsername());
		model.addAttribute("students" , students);
		return "TeacherResetStudentPassword";
	}
	@RequestMapping(value = "/reset-password" , method = {RequestMethod.PUT , RequestMethod.POST})
	public String resetPassword(@Valid Students student,BindingResult bindingResult ,RedirectAttributes redirectAttributes,Model model, HttpSession session) {
				
		Students students = teacherService.findStudent(student.getId());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("username",students.getUsername());	
			System.out.println("Error is"+bindingResult.getErrorCount());
			return "TeacherResetStudentPassword";
		}
		
		students.setPassword(student.getPassword());
		
		teacherService.saveResetPasswordStudent(students);
		redirectAttributes.addFlashAttribute("message", "Reset Password Successfull");
		return "redirect:/teacher/view-students";
	}
	
	
	
	
	@GetMapping("/subject-students")
	public String studentsSubjectView(Model model , HttpSession session) {
		List<Students> findStudentsByTeacherId = teacherService.findStudentsByTeacherId((long) session.getAttribute("teacherid"));
		model.addAttribute("studentList",findStudentsByTeacherId);
		return "TeacherStudentSubjectView";
	}

	@GetMapping("/add-subject")
	public String addStudentSubject(@RequestParam("id") long id , Model model , Subjects subjects) {
		
		Students students = teacherService.findStudent(id);
		model.addAttribute("students" , students);
	
		subjects.setStudentid(id);
		
		model.addAttribute("subjects",subjects);
		return "TeacherAddStudentSubject";
	}

	@PostMapping("/add-subjects")
	public String addStudentSubjects(@Valid Subjects subjects,BindingResult bindingResult ,Model model,RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			
			Students students = teacherService.findStudent(subjects.getStudentid());
			model.addAttribute("students",students);
			return "TeacherAddStudentSubject";
		}
		
		subjectService.addSubject(subjects);
		redirectAttributes.addFlashAttribute("message", "Successfully added a subject");
		return "redirect:/teacher/add-subject";	
	}
	
	@GetMapping("/student-subject")
	public String studentSubjectsView(@RequestParam("id") long studentId , Model model) {
		Students findStudent = teacherService.findStudent(studentId);
		List<Subjects> findSubjectsByStudentId = subjectService.findSubjectsByStudentId(studentId);
		model.addAttribute("student" ,findStudent);
		model.addAttribute("subjectList" ,findSubjectsByStudentId);
		return "TeacherStudentSubjectListView";
	}
	@GetMapping("/grades-student")
	public String studentGradesView(Model model , HttpSession session) {
		List<Students> findStudentsByTeacherId = teacherService.findStudentsByTeacherId((long)session.getAttribute("teacherid"));
		
		model.addAttribute("studentList",findStudentsByTeacherId);
		return "TeacherStudentGradesView";
	}
	@GetMapping("/update-grade")
	public String addStudentGradeView(@RequestParam("id") long studentId , Model model) {
		
		Students findStudent = teacherService.findStudent(studentId);
		
		List<Subjects> findSubjectsByStudentId = subjectService.findSubjectsByStudentId(studentId);
		
		model.addAttribute("student" ,findStudent);
		model.addAttribute("studentGrades",findSubjectsByStudentId);
		
		return "TeacherStudentGrades";
	}
	
	@GetMapping("/add-grades")
	public String addGrade(@RequestParam("id") long subjectId ,Grades grades, Model model) {
		
		Grades findGrades = gradeService.findGradesBySubjectId(subjectId);
	
		if(findGrades == null)
		{
			grades.setSubjectid(subjectId);
			model.addAttribute("grades",grades);
			return "TeacherAddStudentGrades";
		}
		
		grades.setSubjectid(subjectId);		
		model.addAttribute("grades" , findGrades);
		return "TeacherAddStudentGrades";
		
	}
	@RequestMapping(value="/add-grades" , method = {RequestMethod.POST , RequestMethod.PUT})
	public String addGrades(Grades grades ,RedirectAttributes redirectAttributes) {	
		
		Grades findGradesBySubjectId = gradeService.findGradesBySubjectId(grades.getSubjectid());
		
		if(findGradesBySubjectId == null) {
			Grades grade = new Grades();
			grade.setFirstgrading(grades.getFirstgrading());
			grade.setSecondgrading(grades.getSecondgrading());
			grade.setThirdgrading(grades.getThirdgrading());
			grade.setFourthgrading(grades.getFourthgrading());
			grade.setSubjectid(grades.getSubjectid());
			gradeService.saveGrades(grade);
			return "redirect:/teacher/mainview";
		}
	
		findGradesBySubjectId.setFirstgrading(grades.getFirstgrading());
		findGradesBySubjectId.setSecondgrading(grades.getSecondgrading());
		findGradesBySubjectId.setThirdgrading(grades.getThirdgrading());
		findGradesBySubjectId.setFourthgrading(grades.getFourthgrading());
		findGradesBySubjectId.setSubjectid(grades.getSubjectid());
		
		gradeService.saveGrades(findGradesBySubjectId);
		
		redirectAttributes.addFlashAttribute("message","Added grade successfully");
		
		return "redirect:/teacher/grades-student";
	}
	
	@GetMapping("/view-grades")
	public String viewStudentGrades(@RequestParam("id") long studentId , Model model) {
	
		List<Subjects> findSubjectsByStudentId = subjectService.findSubjectsByStudentId(studentId);
		model.addAttribute("studentSubjects" , findSubjectsByStudentId);
		
		return "TeacherStudentSubjectList";
	}
	@GetMapping("/list-grades")
	public String listGradesView(@RequestParam("id") long subjectId , Model model) {
		
		Subjects findSubjectById = subjectService.findSubjectById(subjectId);
		List<Grades> findGradesBySubjectId = gradeService.findGrades(subjectId);
		
		model.addAttribute("gradesList" , findGradesBySubjectId);
		model.addAttribute("subject" ,findSubjectById);
		return "TeacherStudentGradesList";
	}
	
	@GetMapping("/manage-profile")
	public String manageProfile(Model model , HttpSession session) {
		
		Teachers teachers = teacherService.findTeacherById((long)session.getAttribute("teacherid"));
		model.addAttribute("teachers",teachers);
		
		return "TeacherProfileView";
	}
	
	@RequestMapping(value = "/manage-profile",method = {RequestMethod.PUT,RequestMethod.POST})
	public String processManageProfile(@Valid Teachers teachers,BindingResult bindingResult, HttpSession session ,RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			return "TeacherProfileView";
		}
		
		teacherService.updateTeacher(teachers ,(long) session.getAttribute("teacherid"));
		
		redirectAttributes.addFlashAttribute("message","Successfully updated profile");
		
		return "redirect:/teacher/manage-profile";
	}
	
	@GetMapping("/password-reset")
	public String passwordReset(Model model, HttpSession session) {
		
		Teachers teachers = teacherService.findTeacherById((long)session.getAttribute("teacherid"));
		teachers.setPassword(null);
		model.addAttribute("username",teachers.getUsername());
		model.addAttribute("teachers",teachers);
		
		return "TeacherPasswordResetView";
		
	}
	@RequestMapping(value = "/password-reset" , method = {RequestMethod.PUT , RequestMethod.POST})
	public String processPassworReset(@Valid Teachers teachers,BindingResult bindingResult,HttpSession session ,
			RedirectAttributes redirectAttributes ,Model model) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("Erros is "+bindingResult.getErrorCount());
			model.addAttribute("username",teachers.getUsername());
			return "TeacherPasswordResetView";
		}
		teacherService.teacherPasswordReset(teachers, (long)session.getAttribute("teacherid"));
		redirectAttributes.addFlashAttribute("message","Reset password successfully");
		return "redirect:/teacher/password-reset";
	}
	
	
	
	
	
}
