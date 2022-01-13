package com.example.finalproject.view.Fragment.newpost

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentNewPostBinding

class NewPostFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_new_post, container, false)
        val imgr: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        val addPostBtn = v.findViewById<Button>(R.id.postButton)
        val postEditText = v.findViewById<EditText>(R.id.postEditText)

        addPostBtn.isEnabled = !postEditText.text.isEmpty()
        postEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                addPostBtn.isEnabled = !postEditText.text.isEmpty()
            }
        })


        addPostBtn.setOnClickListener {

        }



        return v
    }

}