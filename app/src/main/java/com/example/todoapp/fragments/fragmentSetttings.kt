package com.example.todoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentSetingsBinding

class fragmentSetttings:Fragment() {
    lateinit var binding:FragmentSetingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetingsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}