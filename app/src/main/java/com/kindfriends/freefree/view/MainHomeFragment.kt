package com.kindfriends.freefree.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kindfriends.freefree.CoreConfigDef

import com.kindfriends.freefree.R
import com.kindfriends.freefree.data.TongPanListItem

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainHomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainHomeFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main_home, container, false)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainHomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MainHomeFragment {
            val fragment = MainHomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

//    private fun getItems(){
//        mitemList.add(TongPanListItem(1,"pig","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(2,"Penguin","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(3,"Eagle","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(4,"Rabbit","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(5,"Dolphin","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(6,"Snek","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(7,"Seal","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(8,"Rhino","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(9,"Leopard","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//        mitemList.add(TongPanListItem(10,"Hippo","https://pbs.twimg.com/profile_images/913105871244115968/IRxddKR7_400x400.jpg","201710011100","201710201100","10월 통판입니다.","The domestic dog (Canis lupus familiaris or Canis familiaris) is a member of genus Canis (canines) that forms part of the wolf-like canids, and is the most widely abundant carnivore. The dog and the extant gray wolf are sister taxa, with modern wolves not closely related to the wolves that were first domesticated. The dog was the first domesticated species and has been selectively bred over millennia for various behaviors, sensory capabilities, and physical attributes",
//                "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1","오소마츠상","스티커;컵;아크릴스탠드;책", CoreConfigDef.TongPanListBasicView))
//
//    }
}// Required empty public constructor
