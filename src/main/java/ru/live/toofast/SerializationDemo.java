package ru.live.toofast;

import java.io.*;
import java.util.Arrays;

public class SerializationDemo {

    private static class Cat implements Serializable {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Кот " + name;
        }
    }

    public static void main(String[] args) {
        byte[] byteCat = null;
        try (ByteArrayOutputStream barrOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(barrOut)) {
            Cat catOut = new Cat("Юра");
            objOut.writeObject(catOut);
            byteCat = barrOut.toByteArray();
            System.out.println("Кот до сериализации: " + catOut);
            System.out.println("Вот так он выглядит в байтовом представлении: " + Arrays.toString(byteCat));
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (ByteArrayInputStream barrIn = new ByteArrayInputStream(byteCat);
             ObjectInputStream objIn = new ObjectInputStream(barrIn)) {
            Cat catIn = (Cat) objIn.readObject();
            System.out.println("Вот такого кота мы восстановили из набора байтов: " + catIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}