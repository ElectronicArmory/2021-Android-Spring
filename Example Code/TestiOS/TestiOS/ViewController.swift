//
//  ViewController.swift
//  TestiOS
//
//  Created by Mike Z on 4/20/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var outputLabel: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
    }


    @IBAction func saveName(_ sender: Any) {
        let name = nameTextField.text
        NSLog(name!)
        outputLabel.text = name
    }
}

