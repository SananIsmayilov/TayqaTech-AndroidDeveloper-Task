import com.google.gson.annotations.SerializedName




data class CountryResponse (

	@SerializedName("countryList") val countryList : List<CountryList>
)