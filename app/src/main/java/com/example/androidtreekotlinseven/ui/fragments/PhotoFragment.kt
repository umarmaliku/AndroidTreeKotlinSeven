package com.example.androidtreekotlinseven.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidtreekotlinseven.ui.adapters.ImageAdapter
import com.example.dz_7_5_internet.databinding.FragmentPhotoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private var binding: FragmentPhotoBinding? = null
    private val viewModel by viewModels<PhotoViewModel>()
    private var imageAdapter = ImageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        setClick()
    }

    private fun onClick() {
        binding?.rvPhoto?.adapter = imageAdapter
    }

    private fun setClick() {
        if (checkForInternet(requireContext())) {
            viewModel.photoLiveData.observe(viewLifecycleOwner) {
                imageAdapter.submitList(it)
                Toast.makeText(requireContext(), "Connected", Toast.LENGTH_SHORT).show()
            }
            viewModel.errorLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        } else {
            viewModel.imageLiveData?.observe(viewLifecycleOwner) {
                imageAdapter.submitList(it)
                Toast.makeText(requireContext(), "Disconnected", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
