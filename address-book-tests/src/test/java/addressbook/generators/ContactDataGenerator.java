package addressbook.generators;

import addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/***
 * by rootkoz
 * >(((*>
 */


public class ContactDataGenerator {
    @Parameter(names = "-c", description = "contacts count" )
    public int count;

    @Parameter(names = "-f", description = "target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);

        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastName(),contact.getMobilePhone()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withName(String.format("Name %s", i)).withMobilePhone(String.format("+7(911) 616-10-%s", i)).withLastName(String.format("Footer %s", i)));
        }
        return contacts;
    }
}
