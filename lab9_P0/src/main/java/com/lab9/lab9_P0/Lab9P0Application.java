package com.lab9.lab9_P0;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Year;
import java.util.Scanner;

@SpringBootApplication
public class Lab9P0Application implements CommandLineRunner {
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MasinaJDBCRepository masinaJDBCRepository;


	public void adaugareMasina(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("NrInmatriculare: ");
		String nrInmatriculare = scanner.nextLine();
		System.out.println("Marca: ");
		String marca = scanner.nextLine();
		System.out.println("anulFabricatiei: ");
		int anulFabricatiei = scanner.nextInt();
		System.out.println("Culoarea: ");
		String culoare = scanner.next();
		System.out.println("NrKm: ");
		int nrKm = scanner.nextInt();

		Masina masina=new Masina(nrInmatriculare, marca, anulFabricatiei, culoare, nrKm);
		masinaJDBCRepository.insert(masina);
		logger.info("Masina adaugata: {}", masina);

	}

	public void stergereNrInmatriculare(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("NrInmatriculare: ");
		String nrInmatriculare = scanner.nextLine();
		masinaJDBCRepository.deleteById(nrInmatriculare);
		System.out.println("Masina cu nrInmatriculare: " + nrInmatriculare+" a fost stearsa");
		logger.info("Masina cu nrInmatriculare {} a fost stearsa", nrInmatriculare);
	}

	public void cautareNrInmatriculare(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("NrInmatriculare: ");
		String nrInmatriculare = scanner.nextLine();
		System.out.println(masinaJDBCRepository.findById(nrInmatriculare).toString());
		Masina masina = masinaJDBCRepository.findById(nrInmatriculare);
		logger.info("Masina gasita: {}", masina);
	}

	public void masiniMarcaTastatura(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Marca: ");
		String marca = scanner.nextLine();
		masinaJDBCRepository.findAll().stream().filter(m->m.getMarca().equals(marca)).forEach(System.out::println);
		masinaJDBCRepository.findAll().stream().filter(m->m.getMarca().equals(marca))
				.forEach(m->logger.info("Masini cu marca {}: {}", marca,m));
	}

	public void masiniSub100_000km(){
		masinaJDBCRepository.findAll().stream().filter(m->m.getNrKM()<100000).forEach(System.out::println);
		masinaJDBCRepository.findAll().stream().filter(m->m.getNrKM()<100000).forEach(m->logger.info("{}", m));
	}

	public void masiniMaiNoi5Ani(){
		for(Masina masina : masinaJDBCRepository.findAll()){
			int varsta= Year.now().getValue()-masina.getAnulFabricatiei();
			if(varsta<5){
				System.out.println(masina.toString());
				logger.info("{}",masina);
			}
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(Lab9P0Application.class, args);
	}

	@Override
	public void run(String... args)throws Exception{

		Scanner scanner = new Scanner(System.in);
		int opt;
		do{
			System.out.println("\n1. Adaugare");
			System.out.println("2. Stergere dupa nrInmatriculare");
			System.out.println("3. Cautare dupa nrInmatriculare");
			System.out.println("4. Lista cu toate masinile din baza de date");
			System.out.println("5. NR de masini cu o anumita marca de la tastatura");
			System.out.println("6. Masini sub 100 000km");
			System.out.println("7. Lista cu masini mai noi de 5 ani");
			System.out.println("0. Iseire");
			System.out.println("opt: ");
			opt=scanner.nextInt();
			switch (opt){
				case 1:
					//adaugare masina
					adaugareMasina();
					break;
				case 2:
					//stergere dupa nrInmatriculare
					stergereNrInmatriculare();
					break;
				case 3:
					//cautare dupa nrInmatriculare
					cautareNrInmatriculare();
					break;
				case 4:
					//toate masinile
					System.out.println("\nToate masinile: ");
					masinaJDBCRepository.findAll().forEach(System.out::println);
					masinaJDBCRepository.findAll().stream().forEach(m->logger.info("{}",m));
					break;
				case 5:
					//masini cu o anumita marca de la tastatura
					masiniMarcaTastatura();
					break;
				case 6:
					//masini sub 100 000km
					masiniSub100_000km();
					break;
				case 7:
					//masini mai noi de 5 ani
					masiniMaiNoi5Ani();
					break;
				case 0:
					return;
			}
		}while(true);
	}
}
