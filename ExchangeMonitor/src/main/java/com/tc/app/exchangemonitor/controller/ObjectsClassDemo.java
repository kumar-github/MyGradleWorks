package com.tc.app.exchangemonitor.controller;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple demonstration of the new java.util.Objects class coming with JDK 7.
 */
public class ObjectsClassDemo
{
   private static final Logger LOGGER = Logger.getLogger(ObjectsClassDemo.class.getName());

   /**
    * Demonstrate usage of Objects.requireNonNull(Object).
    *
    * @param shouldNotBeNull String object be passed to Objects.requireNonNull(Object).
    */
   private static void demoObjectsClassNullness(final String shouldNotBeNull)
   {
      String stringToUse = null;
      try
      {
         stringToUse= Objects.requireNonNull(shouldNotBeNull);
      }
      catch (NullPointerException npe)
      {
         LOGGER.severe(npe.toString());
      }
      LOGGER.log(Level.INFO, "Provided String was: ''{0}''", stringToUse);
   }

   /**
    * Demonstrate usage of Objects.requireNonNull(Object,String). This overloaded
    * version of Objects.requireNonNull is generally preferable because the
    * second (String) parameter is the "message" portion of the NullPointerException
    * that is generated. Without this parameter, the message portion is empty.
    *
    * @param shouldNotBeNull String object to be passed to
    *    Objects.requireNonNull(Object,String) where the first (Object) parameter
    *    is the object that should not be null and the second (String) parameter
    *    is the message to display if the first parameter is null.
    */
   private static void demoObjectsClassNullness(
      final String shouldNotBeNull,
      final String messageIfNull)
   {
      String stringToUse = null;
      try
      {
         stringToUse = Objects.requireNonNull(shouldNotBeNull, messageIfNull);
      }
      catch (NullPointerException npe)
      {
         LOGGER.severe(npe.toString());
      }
      LOGGER.log(Level.INFO, "Provided String was: ''{0}''", stringToUse);
   }

   /**
    * Demonstrate use of Objects.toString(Object) with default message if provided
    * object is null.
    *
    * @param objectToStringify Object to call Objects.toString(Object) on.
    */
   private static void demoNullSafeToStringDefault(
      final Object objectToStringify)
   {
      LOGGER.log(Level.INFO, "toString(): {0}", Objects.toString(objectToStringify));
   }

   /**
    * Demonstrate use of Objects.toString(Object, String) with customized String
    * used to "toString()" when the provided object is null.
    *
    * @param objectToStringify Object to call Objects.toString(Object) on.
    * @param toStringIfObjectIsNull String to be shown as result of "toString()"
    *    on a null reference.
    */
   private static void demoNullSafeToStringCustomized(
      final Object objectToStringify, final String toStringIfObjectIsNull)
   {
      LOGGER.log(Level.INFO, "toString(): {0}", Objects.toString(objectToStringify, toStringIfObjectIsNull));
   }

   /**
    * Demonstrate Objects.hash(). The Objects.hashCode() method is also
    * demonstrated and it is handy to be able to safely get a hash code without
    * explicit null check (0 is returned by Objects.hashCode(Object) if the
    * provided Object reference is null). It is also important to note that
    * calling Objects.hash(Object...) on a single object will NOT result in the
    * same hash code returned from Objects.hashCode(Object) on that same object.
    *
    * @param objectsToHash One or more objects to hash.
    */
   private static void demoHash(final Object firstObjectToHash, final Object ... objectsToHash)
   {
      final int numberObjects =
           objectsToHash.length
         + (firstObjectToHash != null ? 1 : 0);
      final int multipleObjectsHash = Objects.hash(objectsToHash);
      LOGGER.log(Level.INFO, "Hash Code for {0} objects: {1}",
                 new Object[]{numberObjects, multipleObjectsHash});
      LOGGER.log(Level.INFO, "Hash code for first object ({0}) of {1} object(s) is: {2}",
                 new Object[]{Objects.toString(firstObjectToHash), numberObjects, Objects.hashCode(firstObjectToHash)});
   }

   /**
    * Demonstrate Objects.equals(Object, Object) method.
    *
    * @param firstObject First object to be compared by Objects.equals(Object,Object).
    * @param secondObject Second object to be compared by Objects.equals(Object,Object).
    */
   private static void demoEquals(final Object firstObject, final Object secondObject)
   {
      final String aproposPhrase =  Objects.equals(firstObject, secondObject)
                                  ? " is equal to "
                                  : " is NOT equal to ";
      LOGGER.log(Level.INFO, "{0}{1}{2}",
                 new Object[]{Objects.toString(firstObject), aproposPhrase, Objects.toString(secondObject)});
   }

   /**
    * Main demonstration executable.
    *
    * @param arguments Command-line arguments; none anticipated.
    */
   public static void main(final String[] arguments)
   {
      demoObjectsClassNullness("Dustin");
      demoObjectsClassNullness(null);

      demoObjectsClassNullness("Dustin", "The String you passed is null!");
      demoObjectsClassNullness(null, "The String you passed is null!");

      final Person person = new Person("Smith", "William");
      Person nullPerson = null;
      try
      {
         nullPerson = new Person("Dump", null);
      }
      catch (NullPointerException npe)
      {
         LOGGER.severe(npe.toString());
      }

      demoNullSafeToStringDefault(person);
      demoNullSafeToStringDefault(nullPerson);

      demoNullSafeToStringCustomized(person, "No such person");
      demoNullSafeToStringCustomized(nullPerson, "No such person");

      demoHash(person, "Dustin");
      demoHash("Dustin", person);
      demoHash(person);
      demoHash("Dustin");
      demoHash(nullPerson);

      final Person person2 = new Person("Smith", "Barney");
      final Person person3 = new Person("Smith", "Barney");
      demoEquals(person, person2);
      demoEquals(person, nullPerson);
      demoEquals(person2, person3);
      demoEquals(nullPerson, null);
   }
}