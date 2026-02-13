package data_providers;

import dto.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static utils.ContactFactory.*;

public class ContactDataProvider {

    @DataProvider
    public Iterator<Contact> dataProviderFromFile() {
        List<Contact> list = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader
                             ("src/test/resources/data.csv/dataProvider.csv"))){
            String line = bufferedReader.readLine();
            while (line!=null){
                String[] splitArray = line.split(",");
                list.add(Contact.builder().name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(splitArray[2])
                        .phone(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Contact> dataProviderEmptyFields_BtnSavePresent() {
        List<Contact> list = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader
                             ("src/test/resources/data.csv/data_provider_btn_save.csv"))){
            String line = bufferedReader.readLine();
            while (line!=null){
                String[] splitArray = line.split(",", -1);
                list.add(Contact.builder().name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(splitArray[2])
                        .phone(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Contact> dataProviderEmptyFields_AlertPresent() {
        List<Contact> list = new ArrayList<>();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader
                             ("src/test/resources/data.csv/data_provider_alert.csv"))){
            String line = bufferedReader.readLine();
            while (line!=null){
                String[] splitArray = line.split(",", -1);
                list.add(Contact.builder().name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(splitArray[2])
                        .phone(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }

    //====================CW11========================

    @DataProvider
    public Iterator<Contact> dataProvider_WrongPhone() {
        List<Contact> list = new ArrayList<>();
        Contact contact = positiveContact();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader
                             ("src/test/resources/data.csv/dp_wrong_phone.csv"))){
            String line = bufferedReader.readLine();
            while (line!=null){
                         list.add(Contact.builder().name(contact.getName())
                        .lastName(contact.getLastName())
                        .email(contact.getEmail())
                        .phone(line)
                        .address(contact.getAddress())
                        .description(contact.getDescription())
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }

    @DataProvider
    public Iterator<Contact> dataProvider_EmptyFields() {
        List<Contact> list = new ArrayList<>();
        Contact contact = positiveContact();
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader
                             ("src/test/resources/data.csv/dp_empty_fields.csv"))){
            String line = bufferedReader.readLine();
            while (line!=null){
                String[] splitArray = line.split(",", -1);
                list.add(Contact.builder().name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(contact.getEmail())
                        .phone(contact.getPhone())
                        .address(splitArray[2])
                        .description(contact.getDescription())
                        .build());
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }
}
