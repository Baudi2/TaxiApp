package ru.startandroid.develop.pulttaxi.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.startandroid.develop.pulttaxi.R
import ru.startandroid.develop.pulttaxi.databinding.FragmentPinCodeBinding
import ru.startandroid.develop.pulttaxi.viewModel.FragmentPinCodeViewModel

@AndroidEntryPoint
class FragmentPinCode : Fragment(R.layout.fragment_pin_code) {

    private lateinit var binding: FragmentPinCodeBinding
    private val viewModel by viewModels<FragmentPinCodeViewModel>()
    private val args: FragmentPinCodeArgs by navArgs()
    private var phoneNumber = ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPinCodeBinding.bind(view)

        viewModel.startCounting()

        phoneNumber = "7${args.phoneNumber}"

        viewModel.second.observe(viewLifecycleOwner) {
            binding.textViewResendCode.text =
                "Повторно отправить СМС с кодом можно будет через $it секунд"
        }

        viewModel.clickable.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.textViewResendCode.setOnClickListener {
                    resentCode()
                }
            }
        }

        binding.buttonReady.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        controlFocus()
        viewModel.requestCode(phoneNumber.toLong())
    }

    private fun postUser() {
        val password = "${binding.codeEditText1.text}${binding.codeEditText2.text}${binding.codeEditText3.text}${binding.codeEdtText4.text}"
        viewModel.postUser(phoneNumber.toLong(), password.toInt())
    }

    private fun showButton() {
        with(binding) {
            val e1 = codeEditText1.text.toString()
            val e2 = codeEditText2.text.toString()
            val e3 = codeEditText3.text.toString()
            val e4 = codeEdtText4.text.toString()

            if (e1.isNotEmpty() && e2.isNotEmpty() && e3.isNotEmpty() && e4.isNotEmpty()) {
                binding.buttonReady.visibility = View.VISIBLE
                binding.buttonReady.setOnClickListener {
                    postUser()
                }
            } else {
                binding.buttonReady.visibility = View.GONE
            }
        }
    }

    private fun resentCode() {
        binding.textViewResendCode.visibility = View.GONE
        Toast.makeText(requireContext(), "Код был отправлен повторно", Toast.LENGTH_SHORT).show()
        viewModel.requestCode(phoneNumber.toLong())
    }

    private fun controlFocus() {
        binding.codeEditText1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEditText1.text.isNotEmpty()) {
                    binding.codeEditText2.requestFocus()
                    showButton()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.codeEditText2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEditText2.text.isNotEmpty()) {
                    binding.codeEditText3.requestFocus()
                    showButton()
                } else {
                    binding.codeEditText1.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.codeEditText3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEditText3.text.isNotEmpty()) {
                    binding.codeEdtText4.requestFocus()
                    showButton()
                } else {
                    binding.codeEditText2.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.codeEdtText4.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEdtText4.text.isEmpty()) {
                    binding.codeEditText3.requestFocus()
                }
                if (binding.codeEdtText4.text.isNotEmpty()) {
                    showButton()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}






