package ru.startandroid.develop.pulttaxi.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.startandroid.develop.pulttaxi.R
import ru.startandroid.develop.pulttaxi.databinding.FragmentMainNumberBinding

class FragmentMainNumber : Fragment(R.layout.fragment_main_number) {

    private lateinit var binding: FragmentMainNumberBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainNumberBinding.bind(view)

        binding.editTextNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.editTextNumber.text.isNotEmpty()) {
                    binding.textViewHint.visibility = View.INVISIBLE
                }
                if (binding.editTextNumber.text.isEmpty()) {
                    binding.textViewHint.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.buttonContinue.setOnClickListener {
            navigateToPin()
        }
    }

    private fun navigateToPin() {
        val action =
            FragmentMainNumberDirections.actionFragmentMainNumberToFragmentPinCode(binding.editTextNumber.text.toString())
        findNavController().navigate(action)
    }
}