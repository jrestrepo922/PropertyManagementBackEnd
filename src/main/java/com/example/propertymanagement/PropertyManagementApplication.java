package com.example.propertymanagement;

import com.example.propertymanagement.model.Property;
import com.example.propertymanagement.model.User;
import com.example.propertymanagement.repository.LeaseRepository;
import com.example.propertymanagement.repository.PropertyRepository;
import com.example.propertymanagement.repository.TenantRepository;
import com.example.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class PropertyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyManagementApplication.class, args);
	}


	@Component
	public class StartUpRunner implements CommandLineRunner {

		@Autowired
		private LeaseRepository leaseRepository;

		@Autowired
		private PropertyRepository propertyRepository;

		@Autowired
		private TenantRepository tenantRepository;

		@Autowired
		private UserRepository userRepository;

		@Override
		public void run(String... args) throws Exception {
			// create some users
			User user1 = new User("Tinto", "1234");
			User user2 = new User("Chente", "7890");
			User user3 = new User("Checo", "nonsense");


			// create properties
			Property property1 = new Property(
					"6360 Wilmington Way",
					"30045",
					"Ga",
					4,
					3,
					"https://images.unsplash.com/photo-1600585154340-be6161a56a0c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aG9tZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=400&q=60"
			);
			Property property2 = new Property(
					"10410 Tropical Way",
					"30022",
					"Ga",
					5,
					4,
					"https://images.unsplash.com/photo-1628624747186-a941c476b7ef?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aG9tZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=400&q=60"
			);
			Property property3 = new Property(
					"31000 Wind Way",
					"30002",
					"Ga",
					1,
					1,
					"https://images.unsplash.com/photo-1600585154363-67eb9e2e2099?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGhvbWVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=400&q=60"
			);
			Property property4 = new Property(
					"2456 Sherlter cove",
					"30090",
					"Ga",
					4,
					3,
					"https://images.unsplash.com/photo-1628624747295-ea5e7fc3d76f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fGhvbWVzfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=400&q=60"
			);

			// add the properties to the users
			// the addProperty method adds user to the property as well
			// this creates a bidirectional mapping
			user1.addProperty(property1);
			user1.addProperty(property2);
			user1.addProperty(property4);
			user2.addProperty(property3);

			// save the users
			// only need to save users since we use cascade save on the
			// properties' field in user
			this.userRepository.save(user1);
			this.userRepository.save(user2);
			this.userRepository.save(user3);
		}
	}
}
