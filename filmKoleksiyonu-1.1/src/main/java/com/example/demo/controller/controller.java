package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.interfaces.filmRepository;
import com.example.demo.interfaces.oyuncuRepository;
import com.example.demo.interfaces.turRepository;
import com.example.demo.models.filmModel;
import com.example.demo.models.oyuncuModel;
import com.example.demo.models.turModel;


@Controller
public class controller {
	

	@Autowired
	filmRepository filmrepository;
	@Autowired
	oyuncuRepository oyuncurepository;
	@Autowired
	turRepository turrepository;
	

	@GetMapping("/filmekle")
	public String film_save(Model model) {
		model.addAttribute("Film", new filmModel());
		return "filmekleme";
	}

	@PostMapping(value = "/filmekle")
	public String film_saved(@Valid @ModelAttribute("filmModel") filmModel filmModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "filmekleme";

		filmrepository.save(filmModel);
		
		return "redirect:/anasayfa";
	}
	long x=0;
	@GetMapping("/oyuncuekle")
	public String oyuncu_save(@RequestParam("filmid") long urunAdi, Model model) {
		
		oyuncuModel oyuncu=new oyuncuModel();
		x=urunAdi;
		model.addAttribute("oyuncu", oyuncu);
		return "oyuncuekle";
	}
	@GetMapping("/arama")
	public String search(@RequestParam("arama") String aranan, Model model) {
		
		ArrayList<oyuncuModel> oyucular=oyuncurepository.findTop10BynameIgnoreCaseContaining(aranan);
		List<filmModel> filmler=filmrepository.findTop10BynameIgnoreCaseContaining(aranan);
		ArrayList<turModel> turleri=turrepository.findTop10ByfilminturuIgnoreCaseContaining(aranan);
		for (oyuncuModel filmModel : oyucular) {
			filmler.add(filmModel.getFilmler());
			
		}
		if(turleri.isEmpty()!=true) {
		for (turModel tur : turleri) {
			for(filmModel film : tur.getFilmlistesi()) {
				filmler.add(film);
			}
			}
		}
	
	
		
		List<filmModel> temizle = filmler.stream()
			     .distinct()
			     .collect(Collectors.toList());
		
		model.addAttribute("filmler", temizle);
		return "ansyfa";
	}
	
	@GetMapping("/sirala")
	public String sort( Model model) {
		
		model.addAttribute("filmler", filmrepository.findByOrderByYearAsc());
		return "ansyfa";
	}
	@GetMapping("/basla")
public String create( Model model) {
		filmModel bir=new filmModel();
		filmModel iki=new filmModel();
		filmModel uc=new filmModel();
		filmModel dort=new filmModel();
		bir.setName("Avengers");
		bir.setContent("marvel cartoon");
		bir.setMedia("");
		bir.setYear(20017);
		iki.setName("MADNESS");
		iki.setContent("");
		iki.setMedia("");
		iki.setYear(2018);
		dort.setName("Star Wars");
		dort.setContent("LUCAS");
		dort.setMedia("");
		dort.setYear(20020);
		uc.setName("SCREAM");
		uc.setContent("");
		uc.setMedia("");
		uc.setYear(2019);
		oyuncuModel x=new oyuncuModel();
		oyuncuModel y=new oyuncuModel();
		oyuncuModel z=new oyuncuModel();
		x.setName("Eve");
		x.setSurname("Enginers");
		x.setContent("Asistant Cha");
		y.setName("Child");
		y.setSurname("Cold");
		y.setContent("Lead");
		z.setName("Fire");
		z.setSurname("hot");
		z.setContent("figure");
		turModel tur1=new turModel();
		tur1.setFilminturu("AKSİYON");
		turModel tur2=new turModel();
		tur2.setFilminturu("HEYECAN");
		turModel tur3=new turModel();
		tur3.setFilminturu("SAVAŞ");
		turModel tur4=new turModel();
		tur4.setFilminturu("GERİLİM");
		x.setFilmler(bir);
		y.setFilmler(iki);
		z.setFilmler(uc);
		oyuncurepository.save(x);
		oyuncurepository.save(y);
		oyuncurepository.save(z);
		bir.setTur(tur1);
		iki.setTur(tur4);
		uc.setTur(tur3);
		dort.setTur(tur2);
		ArrayList<filmModel> films=new ArrayList<filmModel>();
		films.add(bir);
		films.add(iki);	films.add(uc);	films.add(dort);
		filmrepository.saveAll(films);
		model.addAttribute("filmler", filmrepository.findAll());
		return "redirect:/anasayfa";
	}
	@PostMapping(value = "/oyuncuekle")
	public String char_save(@Valid @ModelAttribute("oyuncu") oyuncuModel oyuncuModel, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "oyuncuekle";
		
		oyuncuModel.setFilmler(filmrepository.findById(x).get());
		oyuncurepository.save(oyuncuModel);
		
		return "redirect:/anasayfa";
	}
	
	@GetMapping("/anasayfa")
	public String filmlev(Model model) {
		
	    List x=filmrepository.findAll();
	    model.addAttribute("filmler", x);
	    return "ansyfa";
	}
	@GetMapping("/oyunculari")
	public String actors(@RequestParam("filmid") long urunAdi, Model model) {
		
	    List x=filmrepository.findById(urunAdi).get().getOyuncular();
	    model.addAttribute("oyuncular", x);
	    return "oyuncu";
	}
	
	@GetMapping("/filmdüzenle")
	public String filmEdit(@RequestParam("id") long id, Model model) {
	
	    
	    model.addAttribute("Film",filmrepository.findById(id).get());
	    return "index";
	}
	
	
	@GetMapping("/filmsil")
	public String deleteFilm(@RequestParam("id") long id, Model model) {
	   if (filmrepository.findById(id).get().getOyuncular().isEmpty()==true) 
	   { 
		   filmrepository.deleteById(id);
		   return "redirect:/anasayfa";
	   }
	   else
		   return "redirect:/anasayfa";
	
	   
	}

	@GetMapping("/oyuncusil")
	public String deleteActor(@RequestParam("id") long id, Model model) {
	
	    oyuncurepository.deleteById(id);
	    return "redirect:/anasayfa";
	}

	
	

}
