package com.revature.spring_mvc.web.dtos;

import java.util.Objects;

public class SignUp {
	
		private String name;
		private String email;
	    private String username;
	    private String password;
		public SignUp(String name, String email, String username, String password) {
			super();
			this.name = name;
			this.email = email;
			this.username = username;
			this.password = password;
		}
		public SignUp() {
			super();
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "SignUp [name=" + name + ", email=" + email + ", username=" + username + ", password=" + password
					+ "]";
		}
		@Override
		public int hashCode() {
			return Objects.hash(email, name, password, username);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SignUp other = (SignUp) obj;
			return Objects.equals(email, other.email) && Objects.equals(name, other.name)
					&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
		}
	    
	    
	
}
