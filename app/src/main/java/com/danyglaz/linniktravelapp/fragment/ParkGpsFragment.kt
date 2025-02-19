package com.danyglaz.linniktravelapp.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danyglaz.linniktravelapp.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.Route
import com.yandex.mapkit.transport.masstransit.Session
import com.yandex.mapkit.transport.masstransit.TimeOptions
import com.yandex.runtime.Error
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError

class ParkGpsFragment : Fragment(), Session.RouteListener {
    private lateinit var mapView: MapView
    private lateinit var pedestrianRouterSession: Session
    private val start_point = Point(55.752329, 37.609381)
    private val alex_gard=Point(55.752077, 37.613614)
    private val museon=Point(55.733055, 37.604646)
    private val gorky_park=Point(55.731474, 37.603431)
    private val neskuch_gard=Point(55.718356, 37.591445)
    private val andrew_water=Point(55.714627, 37.582870)
    private val vorobey=Point(55.711473, 37.544458)
    private val mgu_gard=Point(55.707701, 37.526814)
    private val finish_point = Point(55.710449, 37.517074)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MapKitFactory.initialize(requireContext())
        DirectionsFactory.initialize(requireContext())
        TransportFactory.initialize(requireContext())


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_park_gps, container, false)
        mapView = rootView.findViewById(R.id.mapview)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.mapWindow.map.move(
            CameraPosition(start_point, 5.0f, 0.0f, 0.0f),
        )

        mapView.map.mapObjects.addPlacemark(start_point)
        mapView.map.mapObjects.addPlacemark(alex_gard)
        mapView.map.mapObjects.addPlacemark(museon)
        mapView.map.mapObjects.addPlacemark(gorky_park)
        mapView.map.mapObjects.addPlacemark(neskuch_gard)
        mapView.map.mapObjects.addPlacemark(andrew_water)
        mapView.map.mapObjects.addPlacemark(vorobey)
        mapView.map.mapObjects.addPlacemark(mgu_gard)
        mapView.map.mapObjects.addPlacemark(finish_point)

        // Запрашиваем маршрут
        val timeOptions= TimeOptions()
        val requestPoints = listOf(
            RequestPoint(start_point,RequestPointType.WAYPOINT,null,null),
            RequestPoint(alex_gard,RequestPointType.VIAPOINT,null,null),
            RequestPoint(museon, RequestPointType.VIAPOINT, null, null),
            RequestPoint(neskuch_gard,RequestPointType.VIAPOINT,null,null),
            RequestPoint(andrew_water, RequestPointType.VIAPOINT, null, null),
            RequestPoint(vorobey, RequestPointType.VIAPOINT, null, null),
            RequestPoint(mgu_gard, RequestPointType.VIAPOINT, null, null),
            RequestPoint(finish_point, RequestPointType.WAYPOINT,null,null)
        )



        pedestrianRouterSession =TransportFactory.getInstance().createPedestrianRouter().requestRoutes(
            requestPoints,
            timeOptions,
            this
        )

    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onMasstransitRoutes(routes: MutableList<Route>) {
        if (routes.isNotEmpty()) {
            val route = routes[0]
            mapView.map.mapObjects.addPolyline(route.geometry)
        }
    }

    override fun onMasstransitRoutesError(error: Error) {
        val errorMessage = when (error) {
            is RemoteError -> "Remote error: ${error.isValid}"
            is NetworkError -> "Network error: Please check your internet connection"
            else -> "Unknown error"
        }
        println("Error getting driving routes: $errorMessage")
    }

}
