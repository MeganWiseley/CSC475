import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotoViewModel : ViewModel() {
    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> get() = _imageUrls

    init {
        // Example image URLs
        _imageUrls.value = listOf(
            "https://megancookphotography.com/wp-content/uploads/2021/07/DSC_0267-1024x683.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0188-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0170-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0181-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC_0075-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC2261-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC2277-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2021/06/DSC2235-scaled.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0195.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0135-678x1024.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0162-1024x678.jpg",
            "https://megancookphotography.com/wp-content/uploads/2020/10/DSC0187-980x649.jpg"

        )
    }
}
