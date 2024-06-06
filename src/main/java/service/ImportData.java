package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entities.Additive;
import entities.Allergene;
import entities.Brand;
import entities.Category;
import entities.Ingredient;
import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;



public class ImportData {

	public static void getDataFromCSV() throws IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configBdd");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction= em.getTransaction();
		Path foodPath= Paths.get("C:/Data/Info/CDA/ToutLesCours/TPautoformationJ2/open-food-facts.csv");
		List<String> lines=Files.readAllLines(foodPath);
		
		
		
		
		

		for(int i =1; i<500; i++) {
			
			
			//debut transaction pour un produit
			
			String[] elements = lines.get(i).split("\\|", -1);

			if(elements.length==31) {
				transaction.begin();
				Product p= new Product();
			
			
			//Gestion du nom/grade nutritionnel
			p.setName(elements[2]);
			p.setNutritionalGrade(elements[3]);
			
			//gestion de la catégorie
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c where c.libelle =:libelle", Category.class);
			query.setParameter("libelle", elements[0].toLowerCase());
			List<Category> categorie = query.getResultList();
			if(categorie.isEmpty()) {
				Category c= new Category();
				c.setLibelle(elements[0].toLowerCase());
				em.persist(c);
				p.setCategory(c);
			}
			else {
				p.setCategory(categorie.get(0));
			}
			
			//gestion de la marque
			TypedQuery<Brand> queryB = em.createQuery("SELECT c FROM Brand c where c.name =:name", Brand.class);
			queryB.setParameter("name", elements[1].toLowerCase());
			List<Brand> brand = queryB.getResultList();
			if(brand.isEmpty()) {
				Brand b= new Brand();
				b.setName(elements[1].toLowerCase());
				em.persist(b);
				p.setBrand(b);
			}
			else {
				p.setBrand(brand.get(0));
			}
			
			
			
			//gestion des ingredients
			String[] ingredients = elements[4].split("[,;\\-]");
			Set<Ingredient> ingredientsList =new HashSet<Ingredient>();
			for (int y=0; y<ingredients.length; y++) {
				TypedQuery<Ingredient> queryI = em.createQuery("SELECT i FROM Ingredient i where i.libelle =:libelle", Ingredient.class);
				queryI.setParameter("libelle", ingredients[y].trim().toLowerCase());
				List<Ingredient> ingredientsData = queryI.getResultList();
				if(ingredientsData.isEmpty()) {
					Ingredient ing= new Ingredient();
					ing.setLibelle(ingredients[y].trim().toLowerCase());
					em.persist(ing);
					ingredientsList.add(ing);
				}
				else {
					ingredientsList.add(ingredientsData.get(0));
				}
			}
			p.setIngredients(ingredientsList);
			
			//gestion additifs
			String[] additive = elements[29].split("[,;\\-]");
			Set<Additive> additiveList =new HashSet<Additive>();
			for (int z=0; z<additive.length; z++) {
				TypedQuery<Additive> queryA = em.createQuery("SELECT a FROM Additive a where a.libelle =:libelle", Additive.class);
				queryA.setParameter("libelle", additive[z].trim().toLowerCase());
				List<Additive> additiveData = queryA.getResultList();
				if(additiveData.isEmpty()) {
					Additive ad= new Additive();
					ad.setLibelle(additive[z].trim().toLowerCase());
					em.persist(ad);
					additiveList.add(ad);
				}
				else {
					additiveList.add(additiveData.get(0));
				}
			}
			p.setAdditives(additiveList);
			
			//gestion allergenes
			String[] allergenes = elements[28].split("[,;\\-]");
			Set<Allergene>allergeneList =new HashSet<Allergene>();
			for (int x=0; x<allergenes.length; x++) {
				TypedQuery<Allergene> queryAl = em.createQuery("SELECT al FROM Allergene al where al.libelle =:libelle", Allergene.class);
				queryAl.setParameter("libelle", allergenes[x].trim().toLowerCase());
				List<Allergene> additiveData = queryAl.getResultList();
				if(additiveData.isEmpty()) {
					Allergene al= new Allergene();
					al.setLibelle(allergenes[x].trim().toLowerCase());
					em.persist(al);
					allergeneList.add(al);
				}
				else {
					allergeneList.add(additiveData.get(0));
				}
			}
			p.setAllergenes(allergeneList);
			
			
			
			em.persist(p);
			transaction.commit();
			}
		}
		
		
	}

}