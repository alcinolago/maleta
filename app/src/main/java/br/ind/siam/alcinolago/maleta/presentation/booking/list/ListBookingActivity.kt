package br.ind.siam.alcinolago.maleta.presentation.booking.list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.ind.siam.alcinolago.maleta.Constants
import br.ind.siam.alcinolago.maleta.R
import br.ind.siam.alcinolago.maleta.core.BaseActivity
import br.ind.siam.alcinolago.maleta.core.util.Util
import br.ind.siam.alcinolago.maleta.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_booking_list.*
import kotlinx.android.synthetic.main.toolbar_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBookingActivity : BaseActivity() {

    private val listBookingViewModel: ListBookingViewModel by viewModel()

    //private var producerEntityList = ArrayList<ProducerEntity>()
    //private lateinit var producerAdapter: ProducerAdapter
    private lateinit var searchTerm: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_list)

        checkLoggedin()
        initViews()
        setupToolbar(Constants.TOOLBAR_BOOKING)
        observeViewModel()
        observeisLoggedIn()
        observeIsLogout()
        configureRecyclerView()
        configureAdapterRecyclerView()

        //listProducerViewModel.getProducers()
    }

    private fun checkLoggedin() {
        listBookingViewModel.checkIsFirstAccess()
    }

    override fun initViews() {
        //fabAddBooking.setSafeOnClickListener { fabViewAddProducer() }
        recyclerViewBooking.addOnScrollListener(hideFabButtonOnScroll())
    }

    override fun setupToolbar(title: String) {

        setSupportActionBar(toolbar_main)
        supportActionBar?.title = title
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_booking_toolbar, menu)

        val sv = menu.findItem(R.id.action_search_booking).actionView as SearchView

        sv.setOnCloseListener(SearchView.OnCloseListener {

            //listProducerViewModel.getProducers()
            //showProgress()
            Util.hideKeyboard(this@ListBookingActivity)
            sv.onActionViewCollapsed()

            return@OnCloseListener true
        })

        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchTerm = newText.toLowerCase()
                searchProducer(searchTerm)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logoutMenu -> {
                listBookingViewModel.logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun observeViewModel() {

        //showProgress()

//        listProducerViewModel.producersMutableLiveData.observe(this, Observer { producersList ->
//            producerEntityList.clear()
//            producerEntityList.addAll(producersList)
//            producerAdapter.addItems(producersList)
//            hideProgress()
//        })

//        listProducerViewModel.searchProducerMutableLiveData.observe(this, Observer { producersList ->
//            producerEntityList.clear()
//            producerEntityList.addAll(producersList)
//            producerAdapter.addItems(producersList)
//            hideProgress()
//        })

        //listProducerViewModel.getProducers()
    }

    private fun observeisLoggedIn() {
        listBookingViewModel.isLoggedIn.observe(this, {
            it?.getContentIfNotHandled()?.let { isLoggedIn ->
                if (!isLoggedIn)
                    navigateToLogin()
            }
        })
    }

    private fun observeIsLogout() {
        listBookingViewModel.isLogout.observe(this, {
            it?.getContentIfNotHandled()?.let { isLogout ->
                if (isLogout)
                    navigateToLogin()
            }
        })
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun hideFabButtonOnScroll(): RecyclerView.OnScrollListener {

        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fabAddBooking.visibility == View.VISIBLE) {
                    fabAddBooking.hide()
                } else if (dy < 0 && fabAddBooking.visibility != View.VISIBLE) {
                    fabAddBooking.show()
                }
            }
        }
    }

    //private fun fabViewAddProducer(): View.OnClickListener {

    //return View.OnClickListener {
    //    val it = Intent(this@ListBookingActivity, AddProducerActivity::class.java)
    //    startActivity(it)
    //}
    //}

    override fun configureRecyclerView() {

        val layout = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL, false
        )

        recyclerViewBooking.setHasFixedSize(true)

        recyclerViewBooking.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        recyclerViewBooking.layoutManager = layout
    }

    override fun configureAdapterRecyclerView() {

//        producerAdapter = ProducerAdapter(producerEntityList, this, object : ItemClickListener {
//            override fun onItemClick(position: Int) {
//
//                val formCollectIntent = Intent(this@ListBookingActivity, ProducerDetailsActivity::class.java)
//                val bundle = Bundle()
//                val producer = producerEntityList[position]
//                bundle.putParcelable(Constants.PRODUCER_BUNDLE, producer)
//                formCollectIntent.putExtras(bundle)
//                startActivity(formCollectIntent)
//            }
//        })

        //recyclerViewBooking.adapter = producerAdapter
    }

    private fun searchProducer(searchTerm: String) {
        //listProducerViewModel.searchProducer(searchTerm)
    }
}

