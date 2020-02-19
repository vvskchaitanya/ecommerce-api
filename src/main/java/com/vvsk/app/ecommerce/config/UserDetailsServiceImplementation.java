package com.vvsk.app.ecommerce.config;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.vvsk.app.ecommerce.entity.Address;
import com.vvsk.app.ecommerce.entity.Order;
import com.vvsk.app.ecommerce.entity.Phone;
import com.vvsk.app.ecommerce.entity.Product;
import com.vvsk.app.ecommerce.entity.Status;
import com.vvsk.app.ecommerce.entity.User;
import com.vvsk.app.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void addUser() {
		User user = new User();
		user.setName("vvsk");
		user.setPassword("vvsk");
		user.setFirstName("VVSK");
		user.setRole(Arrays.asList("ROLE_ADMIN"));
		Phone phone = new Phone();
		phone.setCode("+91");
		phone.setNumber("999999999");
		phone.setUser(user);
		Order order = new Order();
		order.setBuyer(user);
		Status status = new Status();
		status.setAction("DELIVERED");
		order.setStatus(Arrays.asList(status));
		Product product = new Product();
		product.setBrand("Prodcut Brand");
		product.setSeller(user);
		product.setCategory("Category");
		product.setPrice(99.0);
		Address address = new Address();
		address.setCity("Washington");
		address.setUser(user);
		user.setAddress(Lists.newArrayList(address));
		user.setProducts(Lists.newArrayList(product));
		user.setOrders(Lists.newArrayList(order));
		user.setPhone(phone);
		if (!userRepository.findById(user.getName()).isPresent())
			userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findById(username);
		if (user.isPresent())
			return new org.springframework.security.core.userdetails.User(user.get().getName(),
					user.get().getPassword(), AuthorityUtils.createAuthorityList(getRoles(user.get())));
		else
			throw new UsernameNotFoundException("No User Found");
	}

	public String[] getRoles(User user) {
		String[] roles = new String[user.getRole().size()];
		user.getRole().forEach(role -> {
			roles[user.getRole().indexOf(role)] = role;
		});
		return roles;
	}

}
