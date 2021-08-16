package com.jesusrojo.usersmvvm.presentation.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.usersmvvm.MyApplication
import com.jesusrojo.usersmvvm.R
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.databinding.ItemsLayoutBinding
import com.jesusrojo.usersmvvm.presentation.di.Injection
import com.jesusrojo.usersmvvm.presentation.ui.details.DetailsDialogFragment
import com.jesusrojo.usersmvvm.utils.DebugHelp
import com.jesusrojo.usersmvvm.utils.InternetHelp


abstract class BaseUiActivity : AppCompatActivity() {

    //@Inject private lateinit var  usersAdapter: UsersAdapter // todo 1.1 HILT
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var llm: LinearLayoutManager
    private lateinit var binding: ItemsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.items_layout)

        initMyAdapter()

        initUi()
        title = javaClass.simpleName

        if (savedInstanceState == null) {

            isNetworkNotAvailableShowMessage()
        }
    }

    override fun onDestroy() {
        destroyLeekCanary()
        super.onDestroy()
    }

    // ADAPTER
    private fun initMyAdapter() {
        //  init with manual injection
        usersAdapter = Injection.provideUsersAdapter(ArrayList(),
            this.resources) { data ->
            DetailsDialogFragment.showInfoDialogFragment(this,
                "", data.toString())
        }
///////////////// todo 1.2 HILT
//        usersAdapter.setOnItemClickListener { data ->
//            DetailsDialogFragment.showInfoDialogFragment(this,
//                "", data.toString()) }
/////////////////
    }

    // UI
    private fun initUi() {
        llm = LinearLayoutManager(applicationContext)

        binding.swipeLayoutContainerItems.setOnRefreshListener {
            binding.swipeLayoutContainerItems.isRefreshing = false
            onRefreshAction()
        }
        binding.recyclerViewItems.apply {
            layoutManager = llm
            adapter = usersAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        val childCount = usersAdapter.itemCount
                        val lastPosition = llm.findLastCompletelyVisibleItemPosition()
                        if (childCount - 1 == lastPosition && isGoneProgressBar()) {
                            onScrollToLastPositionRecyclerAction()
                        }
                    }
                }
            })
        }
    }

    private fun onRefreshAction() {
       // if (isNetworkNotAvailableShowMessage()) return
        refreshDatas()
    }

    fun onScrollToLastPositionRecyclerAction() {
        if (isNetworkNotAvailableShowMessage()) return
        fetchNextDatas()
    }

    private fun onClickMenuTopRightDeleteAll() {
        usersAdapter.deleteAll() // we delete all recycler from here,
        // not from observer an also:
        deleteAllCacheAndRoom()
    }

    private fun onClickMenuTopRightDeleteAllCache() {
        usersAdapter.deleteAll() // we delete all recycler from here,
        // not from observer an also:
        deleteAllCache()
    }

    // ABSTRACTS ACTIONS
    protected abstract fun refreshDatas()

    protected abstract fun fetchNextDatas()

    protected abstract fun deleteAllCacheAndRoom()
    protected abstract fun deleteAllCache()


    //UI UPDATES
    protected fun updateUiSuccess(datas: List<User>?) {
        DebugHelp.l("updateUiSuccess")
        hideProgressBar()

        if (datas != null) {
            if (datas.isNotEmpty()) {

                usersAdapter.setNewValues(datas as ArrayList<User>)
                binding.recyclerViewItems.scrollToPosition(0)

                hideTextViewError()
            }else {
                showMsgBadList()
            }
        } else {
            showMsgBadList()
        }
    }

    protected fun updateUiError(message: String?) {
        DebugHelp.l("updateUiError $message")
        hideProgressBar()
        if (message != null && message.isNotEmpty()) {
            showTextViewErrorWithMessage(message)
        } else {
            hideTextViewError()
        }
    }

    protected fun updateUiLoading() {
        DebugHelp.l("updateUiLoading")
        showProgressBar()
        hideTextViewError()
    }


    //UI PROGRESS BAR
    private fun showProgressBar() {
        binding.progressBarItems.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarItems.visibility = View.GONE
    }

    private fun isGoneProgressBar() = binding.progressBarItems.visibility == View.GONE


    //UI TEXT VIEW ERROR
    private fun hideTextViewError() {
        binding.textViewErrorItems.visibility = View.GONE
        binding.textViewErrorItems.text = ""
    }

    private fun showTextViewErrorWithMessage(message: String?) {
        val msg = resources.getString(R.string.error) + ": " +message
        binding.textViewErrorItems.text = msg
        binding.textViewErrorItems.visibility = View.VISIBLE
    }

    private fun showMsgBadList() {
        val message = getString(R.string.list_is_empty) + "\n\n" + getString(R.string.swipe_down_to_get_fresh_data)
        binding.textViewErrorItems.text = message
        binding.textViewErrorItems.visibility = View.VISIBLE
    }


    // MENU TOP RIGHT
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_top_right, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_1 -> {
                usersAdapter.orderByName()
                true
            }
            R.id.menu_item_2 -> {
                usersAdapter.orderByUserName()
                true
            }
            R.id.menu_item_3 -> {
                usersAdapter.orderByEmail()
                true
            }
            R.id.menu_item_4 -> {
                usersAdapter.orderByWebsite()
                true
            }
            R.id.menu_item_5 -> {
                onClickMenuTopRightDeleteAll()
                true
            }
            R.id.menu_item_6 -> {
                onClickMenuTopRightDeleteAllCache()
                true
            }
//            R.id.menu_item_7 -> {
//                //startMyActivity<LiveDataUsersActivity>()
//                true
//            }
//            R.id.menu_item_8 -> {
//                // startMyActivity<FlowUsersActivity>()
//                true
//            }
//            R.id.menu_item_9 -> {
//                // startMyActivity<ResourceUsersActivity>()
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // LEAK CANARY
    private fun destroyLeekCanary() {
        val app = application as MyApplication
        app.mustDieLeakCanary(this)
    }

    // INTERNET ("private", we check everything in this class)
    private fun isNetworkNotAvailableShowMessage(): Boolean {
        ////TODO  HERE OR IN VIEW MODEL?
        //// viewModel.hasInternet = isNetworkNotAvailable()
        if (!InternetHelp.isNetworkNotAvailableShowMessage(this.applicationContext,
                    binding.swipeLayoutContainerItems)) {
            return true
        }
        return false
    }
}