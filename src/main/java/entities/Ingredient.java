package entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingredient {
	@Column(name = "libelle", length = 500)
	private String libelle;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany
	@JoinTable(name="product_ingredient",
	joinColumns=@JoinColumn(name="ingredient_id", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(name="product_id", referencedColumnName="id")
	)
	private Set<Product> products = new HashSet();
	
	

	public Ingredient() {
	}
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Set<Product> getProducts() {
		return products;
	}



	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "[Ingredient=" + libelle + "]";
	}
	
	

}
