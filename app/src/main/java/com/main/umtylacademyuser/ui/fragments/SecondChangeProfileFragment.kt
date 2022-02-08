package com.main.umtylacademyuser.ui.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.main.umtylacademyuser.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_second_change_profile.view.*

class SecondChangeProfileFragment : Fragment() {
    var userEmail = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_change_profile, container, false)

        userEmail = this.arguments!!.getString("userEmail").toString()

        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setCancelable(false)

        view.emailEtProfile.setText(userEmail)

        view.backToFirstFragment.setOnClickListener {
            val fragment = FirstChangeProfileFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_container, fragment)?.commit()
        }

        view.btnSendResetMessage.setOnClickListener {
            progressDialog.show()
            val mAuth = FirebaseAuth.getInstance()
            mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener { task ->
                if (task.isSuccessful)
                    Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.sent),
                        Toast.LENGTH_SHORT
                    ).show()
                progressDialog.cancel()
            }.addOnFailureListener {
                Toast.makeText(
                    requireContext(),
                    resources.getString(R.string.error),
                    Toast.LENGTH_SHORT
                ).show()
                progressDialog.cancel()
            }
        }

        return view
    }
}