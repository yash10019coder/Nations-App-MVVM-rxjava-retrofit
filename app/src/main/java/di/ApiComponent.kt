package di

import com.yash10019coder.nationsmvvmrxjavaapp.model.CountriesService
import com.yash10019coder.nationsmvvmrxjavaapp.viewModel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}