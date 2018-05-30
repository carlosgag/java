package com.javacodegeeks.advanced.objects;

import java.util.Objects;

public class PersonObjects {
	private String email;
	private String firstName;
	private String lastName;

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final PersonObjects other = (PersonObjects) obj;
		if (!Objects.equals(email, other.email)) {
			return false;
		} else if (!Objects.equals(firstName, other.firstName)) {
			return false;
		} else if (!Objects.equals(lastName, other.lastName)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName);
	}
}
