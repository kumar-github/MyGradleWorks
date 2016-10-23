package com.tc.app.exchangemonitor.controller;

import java.util.Objects;

/**
 * Simple class to be used in demonstration of JDK 7's java.util.Objects class.
 *
 * @author Dustin
 */
public class Person
{
   private String lastName;

   private String firstName;

   /**
    * Parameterized constructor for instantiating a Person. Both a non-null first
    * name and a non-null last name must be provided (no "Madonna" or "Lindsay"
    * or "Seal" allowed here [unless you pass one name as an empty String]).
    *
    * @param newLastName This Person instance's last name; must not be null.
    * @param newFirstName This Person instance's first name; must not be null.
    * @throws NullPointerException Thrown if either provided name parameter is
    *   null.
    */
   public Person(final String newLastName, final String newFirstName)
   {
      this.lastName = Objects.requireNonNull(newLastName, "Last name cannot be null.");
      this.firstName = Objects.requireNonNull(newFirstName, "First name cannot be null.");
   }

   public String getLastName()
   {
      return this.lastName;
   }

   public String getFirstName()
   {
      return this.firstName;
   }

   /**
    * NetBeans 6.9-generated equals(Object) method. It used
    * Objects.equals(Object, Object) to avoid the need to check for null on any
    * references before comparing them. This can really clean up equals method
    * implementations.
    *
    * @param obj Object to be compared to me for equality.
    * @return {@code true} if the provided object is considered equal to me;
    *    {@code false} otherwise.
    */
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final Person other = (Person) obj;
      if (!Objects.equals(this.lastName, other.lastName))
      {
         return false;
      }
      if (!Objects.equals(this.firstName, other.firstName))
      {
         return false;
      }
      return true;
   }

   /**
    * NetBeans 6.9-generated hashCode(). It used Objects.hashCode(Object)!
    * 
    * @return Hash code for this instance.
    */
   public int hashCode()
   {
      int hash = 5;
      hash = 97 * hash + Objects.hashCode(this.lastName);
      hash = 97 * hash + Objects.hashCode(this.firstName);
      return hash;
   }

   @Override
   public String toString()
   {
      return this.firstName + " " + this.lastName;
   }
}


