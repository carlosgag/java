package com.javacodegeeks.advanced.objects;

public class PersonEquals implements Cloneable {
	private final String email;

	public PersonEquals(final String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	// Step 0: Please add the @Override annotation, it will ensure that your
	// intention is to change the default implementation.
	@Override
	public boolean equals(Object obj) {
		// Step 1: Check if the ’obj’ is null
		if (obj == null) {
			return false;
		}
		// Step 2: Check if the ’obj’ is pointing to the this instance
		if (this == obj) {
			return true;
		}
		// Step 3: Check classes equality. Note of caution here: please do not use the
		// ’instanceof’ operator unless class is declared as final. It may cause
		// an issues within class hierarchies.
		if (getClass() != obj.getClass()) {
			return false;
		}
		// Step 4: Check individual fields equality
		final PersonEquals other = (PersonEquals) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}

		return true;
	}

	// Please add the @Override annotation, it will ensure that your
	// intention is to change the default implementation.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		// result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		// result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public PersonEquals clone() throws CloneNotSupportedException {
		return (PersonEquals) super.clone();

	}
}
