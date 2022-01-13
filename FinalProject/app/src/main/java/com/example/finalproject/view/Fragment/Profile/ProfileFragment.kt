package com.example.finalproject.view.Fragment.Profile

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.finalproject.R
import androidx.fragment.app.viewModels
import com.example.finalproject.view.Login.LoginActivity
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_profile, container, false)
        val name = v.findViewById<TextView>(R.id.textViewName_Profile)
        val username = v.findViewById<TextView>(R.id.textViewUsername_Profile)
        val Desc = v.findViewById<TextInputEditText>(R.id.editTextDesc)
        val logoutButton = v.findViewById<ImageButton>(R.id.imageButtonLogout)
        val image = v.findViewById<ImageView>(R.id.imageViewProfile)

        val vm: ProfileFragmentViewModel by viewModels()
        vm.getUserAccount().observe(this,{

            username.setText(it.Username)
            name.setText(it.Name)

        })

       /* image.setOnClickListener{
            Picasso.get().load(image).into(image)
        }*/
        logoutButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this.context)
            alertDialog.setTitle("Logout")
            alertDialog.setMessage("Are you sure you want to logout")
            alertDialog.setPositiveButton("Logout"){ dialog, which ->
                val intent = Intent(this.context, LoginActivity::class.java)
                startActivity(intent)

            }
            alertDialog.setNegativeButton("Cancel"){dialog,which ->
                dialog.cancel()
            }
            val exitdialog = alertDialog.create()
            exitdialog.show()


        }

        return v
    }

}
