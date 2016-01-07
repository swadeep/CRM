package com.intelliverse.controller.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.intelliverse.controller.IUserRestController;
import com.intelliverse.model.Users;
import com.intelliverse.repository.UsersRepository;

@RestController
@RequestMapping("/users")
public class UserRestController implements IUserRestController {

	@Autowired
	private UsersRepository repo;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/createUsers")
	public Users createUsers(@RequestBody Users users) {
		return repo.save(users);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/updateUsers/{id}")
	public Users updateUsers(@RequestParam String id, @RequestBody Users users) {
		Users updateUsers = repo.findOne(id);
		updateUsers.setFirstName(users.getFirstName());
		updateUsers.setLastName(users.getLastName());
		updateUsers.setPhoneNumber(users.getPhoneNumber());
		updateUsers.setEmail(users.getEmail());
		updateUsers.setCompany(users.getCompany());
		updateUsers.setCountry(users.getCountry());
		updateUsers.setState(users.getState());
		updateUsers.setCity(users.getCity());
		return repo.save(updateUsers);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/getUserDetails/{id}")
	public Users getUserDetails(@PathVariable("id") String id) {
		return repo.findOne(id);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePassword/{id}")
	public Users updatePassword(@PathVariable("id") String id, @RequestBody Users users) {
		Users updateUsers = repo.findOne(id);
		updateUsers.setPassword(users.getPassword());
		return repo.save(updateUsers);
	}

	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public String forgetPassword(@RequestBody Users users) throws MessagingException, IOException {
		Users foundUser = repo.findByEmail(users.getEmail());
		if (foundUser != null) {
			String secureToken = UUID.randomUUID().toString();
			foundUser.setResetPasswordToken(secureToken);
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			Date expirationDate = calendar.getTime();
			foundUser.setResetPasswordExpires(expirationDate);
			repo.save(foundUser);
			String text = "You are receiving this because you (or someone else) have requested the reset of the password for your account.\n\n"
					+ "Please click on the following link, or paste into your browser to complete the reset password process :\n\n"
					+ ServletUriComponentsBuilder.fromCurrentContextPath().path("/resetpassword")
							.queryParam("_key", secureToken).build().toUriString()
					+ "\n\n If you did not request this, please ignore this email and your password will remain unchanged.";
			sendResetPasswordLink(foundUser.getEmail(), text);
		}

		String responseMessage = "A mail has been sent to your mail box";
		

		return responseMessage;
	}

	private void sendResetPasswordLink(String email, String text) throws MessagingException, IOException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(email);
		helper.setText(text, true);
		helper.setSubject("Password reset request");
		helper.setFrom("swadeeps@yahoo.com");
		mailSender.send(message);
	}

}
