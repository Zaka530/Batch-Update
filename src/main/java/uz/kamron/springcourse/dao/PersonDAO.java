package uz.kamron.springcourse.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.util.pattern.PathPattern;
import uz.kamron.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL="jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME="postgres";
    private static final String PASSWORD="postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка загрузки драйвера PostgreSQL JDBC", e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }


    public List<Person> index() throws SQLException {
        List<Person>people=new ArrayList<>();

        try {
            Statement statement=connection.createStatement();
            String SQL="SELECT * FROM person";
            ResultSet resultSet= statement.executeQuery(SQL);
            while (resultSet.next()){
                Person person=new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) throws SQLException {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        try {
            Statement statement=connection.createStatement();
            String SQL="INSERT INTO person VALUES("+1+",'"+person.getName()+
                    "',"+person.getAge()+",'"+person.getEmail()+"')";

            //INSERT INTO person VALUES(1,'Tom',18,'32mff@mail.ru')

            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}
