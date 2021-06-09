package com.foreks.android.cicek.modules.product

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.foreks.android.cicek.databinding.ActivityProductBinding
import com.foreks.android.cicek.util.Status
import com.foreks.android.cicek.util.setGone
import com.foreks.android.cicek.util.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityProductBinding.inflate(layoutInflater)
    }

    private val viewModel: ProductViewModel by viewModels()

    private val adapter by lazy {
        ProductAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@ProductActivity)
            recyclerView.adapter = adapter
        }
        viewModel.productList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
                    it.data?.let {
                        adapter.items = it
                    }
                }

                Status.LOADING -> {
                    showProgress()
                }

                Status.ERROR -> {
                    hideProgress()
                    showError(it.message)
                }
            }
        })
    }

    private fun hideProgress() {
        viewBinding.progressBar.setGone()
    }

    private fun showProgress() {
        viewBinding.progressBar.setVisible()
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}