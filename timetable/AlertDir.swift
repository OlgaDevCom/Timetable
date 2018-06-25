//
//  AlertDir.swift
//  timetable
//
//  Created by  Stanislav Topanov on 6/24/18.
//  Copyright © 2018  Stanislav Topanov. All rights reserved.
//

import UIKit
import GoogleMobileAds

class AlertDir: UIViewController , UITableViewDelegate, UITableViewDataSource, GADInterstitialDelegate{
    
    @IBOutlet weak var bartxt: UINavigationItem!
    var interstitial: GADInterstitial!

    internal var arrDir = Array<String>()
    internal var arrTrans = Array<Trans>()

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arrTrans.count
    }
    
    @IBAction func back(_ sender: UIBarButtonItem) {
        //dismiss(animated: true, completion: nil)
        showAds()
    }
     
    
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("You selected cell #\(arrTrans[indexPath.row])!")
        if let vc = UIStoryboard(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "AlertInfoDir") as? AlertInfoDir
        {
             vc.trans = arrTrans[indexPath.row]
            self.present(vc, animated: true, completion: nil)
            
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
 
        let cell = tableView.dequeueReusableCell(withIdentifier: "cell") as! CellDir
        let list = arrTrans[indexPath.row].list as Dictionary
        let listat = arrTrans[indexPath.row].listat as Dictionary
        let listto = arrTrans[indexPath.row].listto as Dictionary


        //let idTranc = [-1,-1]
        let id = list[arrDir[0]]!
        let id2 = list[arrDir[1]]!
        //let tm = arrTrans[indexPath.row].listto["\(String(describing: id))"]
    
        let indLast = arrTrans[indexPath.row].list.count - 1
         print("sdfsd \(indLast)")
        
       // let firsttKey =  Array(list)[0].key
       // let lastKey = Array(list)[indLast].key
       // getKey(dict: list, value: "0")
        
        
        print(listat["\(id)"]!)
        print(listto["\(id2)"]!)
       
      //  let lastStat = arrTrans[indexPath.row].list
        cell.tm2.text = listat["\(id2)"]! as? String
        cell.tm.text = listto["\(id)"]! as? String
        cell.dir.text = arrDir[0]
        cell.dir2.text = arrDir[1]
        cell.direction.text = "\(getKey(dict: list, value: "0")) - \(getKey(dict: list, value: "\(indLast)"))"
        
        return cell
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bartxt.title = "\(arrDir[0]) - \(arrDir[1])"
       interstitial = createAndLoadInterstitial()
 
    }
    internal func interstitialDidDismissScreen(_ ad: GADInterstitial) {
        dismiss(animated: true, completion: nil)
        print("interstitialDidDismissScreen")
    }
    func createAndLoadInterstitial() -> GADInterstitial {
        let interstitial = GADInterstitial(adUnitID: "ca-app-pub-5193056650291894/3927462916")
        // work ca-app-pub-5193056650291894/3927462916
        //test ca-app-pub-3940256099942544/4411468910
        interstitial.delegate = self
        interstitial.load(GADRequest())
        return interstitial
    }
    func interstitial(_ ad: GADInterstitial, didFailToReceiveAdWithError error: GADRequestError) {
        //print("interstitial:didFailToReceiveAdWithError: \(error.localizedDescription)")
    }
     private func showAds()
   {
    if interstitial.isReady {
        interstitial.present(fromRootViewController: self)
    } else {
        print("Ad wasn't ready")
        dismiss(animated: true, completion: nil)
    }
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    private func getKey(dict : Dictionary<String, Any>, value : String) -> String
    {
        var key = String()
        for (kind, numbers) in dict {
            if value == "\(numbers)"
            {
              key = kind
            }
        }
        return key
    }
    
  
}

