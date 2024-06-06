package entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	@JoinColumn(name="brand_id")
	private Brand brand;
	private String name;
	private String nutritionalGrade;
	private String nutritionalData;
	
	@ManyToMany
	@JoinTable(name="product_ingredient",
	joinColumns=@JoinColumn(name="product_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="ingredient_id", referencedColumnName="id")
	)
	private Set<Ingredient> ingredients = new HashSet();
	
	@ManyToMany
	@JoinTable(name="product_allergene",
	joinColumns=@JoinColumn(name="product_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="allergene_id", referencedColumnName="id")
	)
	private Set<Allergene> allergenes= new HashSet();
	
	@ManyToMany
	@JoinTable(name="product_additive",
	joinColumns=@JoinColumn(name="product_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="additive_id", referencedColumnName="id")
	)
	private Set<Additive> additives= new HashSet();
	
	
	public Product() {}


	public Product(Category category, Brand brand, String name, String nutritionalGrade, String nutritionalData,
			Set<Ingredient> ingredients, Set<Allergene> allergenes, Set<Additive> additives) {
		this.category = category;
		this.brand = brand;
		this.name = name;
		this.nutritionalGrade = nutritionalGrade;
		this.nutritionalData = nutritionalData;
		this.ingredients = ingredients;
		this.allergenes = allergenes;
		this.additives = additives;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public String getNutritionalGrade() {
		return nutritionalGrade;
	}


	public void setNutritionalGrade(String nutritionalGrade) {
		this.nutritionalGrade = nutritionalGrade;
	}


	public String getNutritionalData() {
		return nutritionalData;
	}


	public void setNutritionalData(String nutritionalData) {
		this.nutritionalData = nutritionalData;
	}



	public Set<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	public Set<Allergene> getAllergenes() {
		return allergenes;
	}


	public void setAllergenes(Set<Allergene> allergenes) {
		this.allergenes = allergenes;
	}


	public Set<Additive> getAdditives() {
		return additives;
	}


	public void setAdditives(Set<Additive> additives) {
		this.additives = additives;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Product [category=" + category + ", brand=" + brand + ", name=" + name + ", nutritionalGrade="
				+ nutritionalGrade + ", ingredients=" + ingredients + ", allergenes=" + allergenes + ", additives="
				+ additives + "]";
	}


	
	
	
	
}
