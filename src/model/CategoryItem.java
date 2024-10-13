package model;

public class CategoryItem {

	private int id;
	private String name;

	public CategoryItem(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}

}
