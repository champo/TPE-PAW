package ar.edu.itba.paw.grupo1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.grupo1.model.User;
import ar.edu.itba.paw.grupo1.repository.PropertyRepository;
import ar.edu.itba.paw.grupo1.repository.UserRepository;
import ar.edu.itba.paw.grupo1.web.BrokerData;

@Controller
public class BrokerController extends BaseController {

	private UserRepository userRepository;
	private PropertyRepository propertyRepository;

	
	@Autowired
	public BrokerController(UserRepository userRepository, PropertyRepository propertyRepository) {
		super();
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;
	}

	@RequestMapping("/brokers")
	protected ModelAndView brokers() {
		ModelAndView mav = new ModelAndView();
		
		List<User> brokers = userRepository.getBrokers();
		List<BrokerData> res = new ArrayList<BrokerData>();
		for (User user : brokers) {

			BrokerData brokerData = new BrokerData(
				user.getId(),
				user.getRealEstateName(), 
				user.getLogoExtension(), 
				propertyRepository.getListedProperties(user).size()
			);
			
			res.add(brokerData);
		}
		
		mav.addObject("brokers", res);
		return render("brokers.jsp", "Brokers", mav);
	}
	
}
