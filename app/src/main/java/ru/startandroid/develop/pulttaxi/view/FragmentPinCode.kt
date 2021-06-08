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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPinCodeBinding.bind(view)

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
    }

    override fun onStart() {
        super.onStart()
        viewModel.startCounting()
        controlFocus()
        val number = "7${args.phoneNumber}"
        viewModel.requestCode(number.toLong())
    }

    private fun resentCode() {
        binding.textViewResendCode.visibility = View.GONE
        Toast.makeText(requireContext(), "Код был отправлен повторно", Toast.LENGTH_SHORT).show()
    }

    private fun controlFocus() {
        binding.codeEditText1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEditText1.text.isNotEmpty()) {
                    binding.codeEditText2.requestFocus()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.codeEditText2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.codeEditText2.text.isNotEmpty()) {
                    binding.codeEditText3.requestFocus()
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
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}






