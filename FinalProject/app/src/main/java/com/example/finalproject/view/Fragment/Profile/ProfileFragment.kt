package com.example.finalproject.view.Fragment.Profile

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import com.example.finalproject.R
import androidx.fragment.app.viewModels
import com.example.finalproject.model.Profile
import com.example.finalproject.view.Login.LoginActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {
lateinit var image: ImageView
lateinit var imageUri: String
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
        image = v.findViewById(R.id.imageViewProfile)
        val dateTV = v.findViewById<TextView>(R.id.textViewDate)
        val imageButton = v.findViewById<ImageButton>(R.id.imageButton)
        //Picasso.get().load(Profile.).placeholder(R.drawable.ic_launcher_background).into(image)
        val updateButton = v.findViewById<Button>(R.id.buttonUpdate)


        val vm: ProfileFragmentViewModel by viewModels()

        vm.getUserAccount().observe(viewLifecycleOwner,{
            username.setText("@${it.Username}")
            name.setText(it.Name)
            if (it.avatar!!.isNotBlank())
                Picasso.get().load(it.avatar).into(image)
            Desc.text = Editable.Factory.getInstance().newEditable(it.description.toString())
            val listDate = it.date?.split(" ")
            dateTV.text = listDate!![1]+ " " + listDate!!.last()

        })

        imageButton.setOnClickListener{

               //onActivityResult(0,0,)
                ImagePicker.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start()
                 }

        updateButton.setOnClickListener {
            //var user = Profile(saveEmail,saveUsername,saveName,imageUri,Desc.text.toString(),saveDate)
            vm.updateUserBio(Desc.text.toString() ).observe(this, {
                if (it)
                    Toast.makeText(this.context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this.context, "Failed to update", Toast.LENGTH_SHORT).show()
            })
        }


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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            imageUri = uri.toString()
            image.setImageURI(uri)
            ProfileFragmentViewModel().updateUserProfile(imageUri,).observe(this, {
                if (it)
                    Toast.makeText(this.context, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this.context, "Failed to update", Toast.LENGTH_SHORT).show()
            })
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this.context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this.context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

}
