package com.babyapps.retrofitguide.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.babyapps.retrofitguide.R
import com.babyapps.retrofitguide.api.RetrofitInstance
import com.babyapps.retrofitguide.adapter.RetrofitAdapter
import com.babyapps.retrofitguide.databinding.FragmentRetrofitBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@AndroidEntryPoint
class RetrofitFragment : Fragment(R.layout.fragment_retrofit) {

    private lateinit var retrofitAdapter: RetrofitAdapter
    private lateinit var binding: FragmentRetrofitBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRetrofitBinding.bind(view)
        binding.retrofitRv.apply {
            retrofitAdapter = RetrofitAdapter()
            adapter = retrofitAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.get()
            } catch (e: HttpException) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                binding.progressBar.isVisible = false
                return@launchWhenCreated

            } catch (e: IOException) {
                Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null) {
                retrofitAdapter.colors = response.body()!!
                Timber.i("Timber says Successful !")
            } else {
                Toast.makeText(activity, "Response can not be taken", Toast.LENGTH_SHORT).show()
                Timber.i("Tiber sees Error !")
            }
            binding.progressBar.isVisible = false

        }


    }


}