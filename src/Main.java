import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Persoana {
    String nume;
    String prenume;
    int varsta;
    String profesie;

    public Persoana(String nume, String prenume, int varsta, String profesie) {
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
        this.profesie = profesie;
    }

    public String toText() {
        return nume + "," + prenume + "," + varsta + "," + profesie;
    }

    public static Persoana fromText(String text) {
        String[] parts = text.split(",");
        return new Persoana(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
    }

    public String toString() {
        return "Nume: " + nume + ", Prenume: " + prenume + ", Vârstă: " + varsta + ", Profesie: " + profesie;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Persoana> listaPersoane = new ArrayList<>();

        System.out.print("Introduceti numele: ");
        String nume = scanner.nextLine();
        System.out.print("Introduceti prenumele: ");
        String prenume = scanner.nextLine();
        System.out.print("Introduceti varsta: ");
        int varsta = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Introduceti profesia: ");
        String profesie = scanner.nextLine();

        Persoana persoana = new Persoana(nume, prenume, varsta, profesie);
        listaPersoane.add(persoana);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("persoane.txt"))) {
            for (Persoana p : listaPersoane) {
                writer.write(p.toText());
                writer.newLine(); 
            }
            System.out.println("Persoana a fost salvată în fișier.");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea datelor în fișier.");
            e.printStackTrace();
        }

        ArrayList<Persoana> listaDinFisier = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("persoane.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listaDinFisier.add(Persoana.fromText(line));
            }

            System.out.println("\nPersoanele citite din fișier:");
            for (Persoana p : listaDinFisier) {
                System.out.println(p);
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea datelor din fișier.");
            e.printStackTrace();
        }
    }
}
