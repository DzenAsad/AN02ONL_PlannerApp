package io.techmeskills.an02onl_plannerapp.screen.login

import io.techmeskills.an02onl_plannerapp.R
import io.techmeskills.an02onl_plannerapp.databinding.FragmentLoginBinding
import io.techmeskills.an02onl_plannerapp.support.NavigationFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import by.kirich1409.viewbindingdelegate.viewBinding

class LoginFragment : NavigationFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewBinding: FragmentLoginBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModel()



    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
    }
}