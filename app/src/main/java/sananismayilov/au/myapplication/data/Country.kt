import com.google.gson.annotations.SerializedName

data class Country (

	@SerializedName("countryId") val countryId : Int,
	@SerializedName("name") val name : String,
	@SerializedName("cityList") val cityList : List<City>
)