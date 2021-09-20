package lk.aditi.ecom_seller.models.categories;

import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("image")
	private String image;

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("id")
	private String id;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"image = '" + image + '\'' + 
			",category_name = '" + categoryName + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}