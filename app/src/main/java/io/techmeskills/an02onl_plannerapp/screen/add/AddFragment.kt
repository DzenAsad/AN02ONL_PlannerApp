package io.techmeskills.an02onl_plannerapp.screen.add

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.techmeskills.an02onl_plannerapp.R
import io.techmeskills.an02onl_plannerapp.databinding.FragmentAddBinding
import io.techmeskills.an02onl_plannerapp.support.NavigationFragment

class AddFragment : NavigationFragment<FragmentAddBinding>(R.layout.fragment_add) {

    override val viewBinding: FragmentAddBinding by viewBinding()
    private val viewModel: AddViewModel by viewBinding()

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
//        viewBinding.toolbar.setPadding(0, top, 0, 0)
//        viewBinding.recyclerView.setPadding(0, 0, 0, bottom)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override val backPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

}