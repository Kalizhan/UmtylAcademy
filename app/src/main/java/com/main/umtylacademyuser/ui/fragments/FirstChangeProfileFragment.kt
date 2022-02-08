package com.main.umtylacademyuser.ui.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.activities.ProfileActivity
import com.main.umtylacademyuser.utils.models.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.ask_dialog.*
import kotlinx.android.synthetic.main.fragment_first_change_profile.view.*

class FirstChangeProfileFragment : Fragment() {
    var userNameToPass = ""
    private var userSurnameToPass = ""
    var userEmailToPass = ""
    private var userPhoneToPass = ""
    private var userDostup_CoursesToPass = ""
    var userPasswordToPass = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_change_profile, container, false)
//        userNameToPass = this.arguments!!.getString("userNameToPass").toString()
//        userSurnameToPass = this.arguments!!.getString("userSurnameToPass").toString()
//        userEmailToPass = this.arguments!!.getString("userEmailToPass").toString()
//        userPhoneToPass = this.arguments!!.getString("userPhoneToPass").toString()
        val sharedPreferences = activity!!.getSharedPreferences("profileData", Context.MODE_PRIVATE)
        userNameToPass = sharedPreferences.getString("userNameToPass", null).toString()
        userSurnameToPass = sharedPreferences.getString("userSurnameToPass", null).toString()
        userPhoneToPass = sharedPreferences.getString("userPhoneToPass", null).toString()
        userEmailToPass = sharedPreferences.getString("userEmailToPass", null).toString()
        userPasswordToPass = sharedPreferences.getString("userPasswordToPass", null).toString()
        userDostup_CoursesToPass = sharedPreferences.getString("userDostup_CoursesToPass", null).toString()

        view.nameEtProfile.setText(userNameToPass)
        view.surnameEtProfile.setText(userSurnameToPass)
        view.phoneEtProfile.setText(userPhoneToPass)

//        view.toSecondFragment.setOnClickListener {
//            val fragment = SecondChangeProfileFragment()
//            val transaction =  fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.nav_container, fragment)?.commit()
//            val mFragmentManager = fragmentManager
//            val mFragmentTransaction = mFragmentManager?.beginTransaction()
//            val mFragment = SecondChangeProfileFragment()
//            val bundle = Bundle()
//            bundle.putString("userEmail", userEmailToPass)
//            mFragment.arguments = bundle
//
//            mFragmentTransaction!!.replace(R.id.nav_container, mFragment).commit()
//        }

        view.saveChanges.setOnClickListener {
            if (view.phoneEtProfile.length() == 11 || view.phoneEtProfile.length() == 12){
                val email = userEmailToPass.replace(".","-")
                val mDbref = FirebaseDatabase.getInstance().reference.child("users").child("clients").child(email)
                val user = User(view.nameEtProfile.text.toString(), view.surnameEtProfile.text.toString(), view.phoneEtProfile.text.toString(), userEmailToPass, userPasswordToPass, userDostup_CoursesToPass)

                val dialog = Dialog(requireContext())
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(true)
                dialog.setContentView(R.layout.ask_dialog)
                dialog.btnYes.setOnClickListener {
                    mDbref.setValue(user)
                    dialog.cancel()
                    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
                }
                dialog.btnNo.setOnClickListener { dialog.cancel() }
                dialog.show()
            }else{
                view.phoneEtProfile.error = resources.getString(R.string.correct_email)
            }

        }

        view.backToActivity.setOnClickListener {
            val intent = Intent(requireActivity(), ProfileActivity::class.java)
            activity!!.finish()
            startActivity(intent)
        }
        return view
    }
}