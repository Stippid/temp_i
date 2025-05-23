package com.controller.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;

@Controller
public class ValidationController {

//***************************************************START ORBAT VALIDATION ***************************************************************//	
	public String arm_descMSG = "Arm Should Contain Maximum 100 Characters"; 
	public Boolean checkArmDescLength(String arm_desc) {
		if(arm_desc.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String arm_codeMSG = "Please Enter 4 Digit Arm Code";
	public Boolean checkArmCodeLength(String arm_code) {
		if(arm_code.length() != 4) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String parent_arm_codeMSG = "Please Enter 2 Digit Parent Arm Code";
	public Boolean checkParentArmCodeLength(String parent_arm_code) {
		if(parent_arm_code.length() != 2) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String parent_arm_NameMSG = "Parent Arm Should Contain Maximum 400 Characters";
	public Boolean checkParentArmNameLength(String parent_arm_Name) {
		if(parent_arm_Name.length() > 400) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String TypesOf_arm_codeMSG = "Please Enter 4 Digit Type Of Arm Code";
	public Boolean checkTypesOfArmCodeLength(String TypesOf_arm_code) {
		if(TypesOf_arm_code.length() != 4) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String TypesOf_arm_NameMSG = "Type Of Arm Should Contain Maximum 400 Characters";
	public Boolean checkTypesOfArmNameLength(String TypesOf_arm_Name) {
		if(TypesOf_arm_Name.length() > 400) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String loc_codeMSG = "Please Enter 5 Digit Location Code";
	public Boolean LocCodeLength(String loc_code) {
		if(loc_code.length() != 5) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String Loc_NameMSG = "Location Should Contain Maximum 400 Characters";
	public Boolean checkLocNameLength(String Loc_Name) {
		if(Loc_Name.length() > 400) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String TypelocMSG = "Type Of Location Should Contain Maximum 50 Characters";
	public Boolean TypeLocLength(String Typeloc) {
		if(Typeloc.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String mod_descMSG = "Modification Should Contain Maximum 100 Characters";
	public Boolean mod_descLength(String mod_desc) {
		if(mod_desc.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String nrs_codeMSG = "Please Enter 5 Digit Nrs Code";
	public Boolean nrs_codeLength(String nrs_code) {
		if(nrs_code.length() != 5) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String nrsMSG = "Nrs Should Contain Maximum 100 Characters";
	public Boolean nrsLength(String nrs) {
		if(nrs.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String goi_Letter_noMSG = "goi Letter No Should Contain Maximum 250 Characters";
	public Boolean checkgoi_Letter_noLength(String goi_Letter_no) {
		if(goi_Letter_no.length() > 250) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String auth_let_noMSG = "Auth Letter No Should Contain Maximum 250 Characters";
	public String Letter_noMSG = "Letter No Should Contain Maximum 250 Characters";
	public Boolean checkLetter_noLength(String Letter_no) {
		if(Letter_no.length() > 250) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String uploaddocumentMSG = "Upload Document Should Contain Maximum 250 Characters";
	public String Upload_authLetterMSG = "Upload Auth Letter No Should Contain Maximum 250 Characters";
	public Boolean checkUpload_authLetterLength(String Upload_authLetter) {
		if(Upload_authLetter.length() > 250) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String Upload_goiLetterMSG = "Upload Goi Letter No Should Contain Maximum 250 Characters";
	public Boolean checkUpload_goiLetterLength(String Upload_goiLetter) {
		if(Upload_goiLetter.length() > 250) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String depotunit_nameMSG = "Depot Name Should Contain Maximum 100 Characters";
	public String command_nameMSG = "Command Name Should Contain Maximum 100 Characters";
	public String unit_nameMSG = "Unit Name Should Contain Maximum 100 Characters";
	public String sponsoring_dteMSG = "Nodal Dte Should Contain Maximum 100 Characters";
	public String typ_of_spl_eqpt_roleMSG = " Type of Spl Eqpt/Role for which Veh will be used Should Contain Maximum 100 Characters";
	public Boolean checkUnit_nameLength(String unit_name) {
		if(unit_name.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String unit_sus_noMSG = "Please Enter 8 Digit Unit SUS No.";
	public String command_susMSG = "Please Enter 8 Digit Command Sus no";
	public String sus_noMSG = "Please Enter 8 Digit SUS No.";
	public String depot_susMSG = "Please Enter 8 Digit Depot Sus no";
	public Boolean sus_noLength(String sus_no) {
		if(sus_no.length() != 8) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }


	public String addressMSG = "Address Should Contain Maximum 200 Characters";
	public Boolean checkAddressLength(String address) {
		if(address.length() > 200) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String force_typeMSG = "Force Type Should Contain Atleast 10 Characters";
	public Boolean checkForceTypeLength(String force_type) {
		if(force_type.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String typ_of_stnMSG = "Type Of Stn Should Contain Atleast 100 Characters";
	public Boolean checkTypeOfStnLength(String typ_of_stn) {
		if(typ_of_stn.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String typ_of_terrainMSG = "Type Of Terrain Should Contain Atleast 100 Characters";
	public Boolean checkTypeOfTerrainLength(String typ_of_terrain) {
		if(typ_of_terrain.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String mode_of_tptMSG = "Type Of Mode Should Contain Atleast 25 Characters";
	public Boolean checkModeOfTptLength(String mode_of_tpt) {
		if(mode_of_tpt.length() > 25) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }

//***************************************************END ORBAT VALIDATION ***************************************************************//
	
//***************************************************START CUE VALIDATION ***************************************************************//
	public String catMSG = "Please Enter Valid Length Of Category ";
	public String typeofcatMSG = "Please Enter Valid Length Of Type of Category";
	public String descMSG = "Please Enter Valid Length Of Description";
	public String remarksMSG = "Please Enter Valid Length Of Remarks";
	public String monthMSG = "Please Enter Valid Length Of Month";
	public String yearMSG = "Please Enter Valid Length Of Year";
	public String rankcatMSG = "Please Enter Valid Length Of Rank Category";
	public String rankMSG = "Please Enter Valid Length Of Rank";
	public String nomenMSG = "Please Enter Valid Length Of Nomenclature";
	public String exwepenoMSG = "Please Enter Valid Length Of Exsisting  WE/PE No";
	public String newwepenoMSG = "Please Enter Valid Length Of New  WE/PE No";
	public String upwepenoMSG = "Please Enter Valid Length Of Approved Uploaded  WE/PE No";
	public String wepetitleMSG = "Please Enter Valid Length Of WE/PE Tittle";
	public String secclassMSG = "Please Enter Valid Length Of Security Classification";
	public String spodireMSG = "Please Enter Valid Length Of Sponsor Directorate";
	public String wepenoMSG = "Please Enter Valid Length Of WE/PE No";
	public String tbltitileMSG = "Please Enter Valid Length Of Table Title";
	public String unitcatMSG = "Please Enter Valid Length Of Unit Category";
	public String spodocnoMSG = "Please Enter Valid Length Of Superseded Document No";
	public String wetpetnoMSG = "Please Enter Valid Length Of WET/PET No";
	public String copywetpetMSG = "Please Enter Valid Length Of Copy WET/PET/FET No";
	public String unittypeMSG = "Please Enter Valid Length Of Unit Type";
	public String wetypeMSG = "Please Enter Valid Length Of WE/PE/FE/GSL";
	public String supWepenoMSG = "Please Enter Valid Length Of Superseeded WE/PE No";
	public String cestypeMSG = "Please Enter Valid Length Of CES Type";
	public String cesnoMSG = "Please Enter Valid Length Of CES/CCES No";
	public String cesNameMSG = "Please Enter Valid Length Of CES/CCES Name ";
	public String mctnoMSG = "Please Enter Valid Length Of MCT No";
	public String senarioMSG = "Please Enter Valid Length Of Senario";
	public String modMSG = "Please Enter Valid Length Of Modification";
	public String incdecMSG = "Please Enter Valid Length Of Increment/Decrement";
	public String locMSG = "Please Enter Valid Length Of Location";
	public String formMSG = "Please Enter Valid Length Of Formation";
	public String unitMSG = "Please Enter Valid Length Of Unit";
	public String itemcodeMSG = "Please Enter Valid Length Of Item Code";
	public String conditionMSG = "Please Enter Valid Length Of General Notes Condition";
	public String catpersMSG = "Please Enter Valid Length Of Category of Personnel";
	public String apptradeMSG = "Please Enter Valid Length Of Common Appt/Trade";
	public String inlieuMCTMSG = "Please Enter Valid Length Of In Lieu MCT";
	public String condiMSG = "Please Enter Valid Length Of Condition";
	public String prfGrpCodeMSG = "Please Enter Valid Length Of PRF Group Code";
	public String cosSecMSG = "Please Enter Valid Length Of COS Section";
	public String prfGrpMSG = "Please Enter Valid Length Of PRF Group";
	public String itemGrpMSG = "Please Enter Valid Length Of Item Group";	
	public String itemCatMSG = "Please Enter Valid Length Of Item Category";
	public String critiEqpMSG = "Please Enter Valid Length Of Critical Equipment";
	public String brdCatStrMSG = "Please Enter Valid Length Of Broad Category of Stores";
	public String accUnitMSG = "Please Enter Valid Length Of Accountng Unit";
	public String classMSG = "Please Enter Valid Length Of Class";
	public String wetpetTypeMSG = "Please Enter Valid Length Of WET/PET/FET Document Type";
	public String visibMSG = "Please Enter Valid Length Of Visibility";
	public String nodleDteMSG = "Please Enter Valid Length Of Nodal Dte";
	public String authLtrnoMSG = "Please Enter Valid Length Of Auth Letter No";
	public String forceTypeMSG = "Please Enter Valid Length Of Force Type";
	public String unitNoMSG = "Please Enter Valid Length Of No of Units";
	public String typeofUnitMSG = "Please Enter Valid Length Of Type of Unit";
	public String typeofDocMSG = "Please Enter Valid Length Of Type Of Documenty";
	public String yearCalMSG = "Please Enter Valid Length Of Year of Calculation";
	public String monthCalMSG = "Please Enter Valid Length Of  Month of Calculation";
	public String docTitleMSG = "Please Enter Valid Length Of Document Title";
	public String supWetpetnoMSG = "Please Enter Valid Length Of Superseeded WET/PET No";
	public String amendDocMSG = "Please Enter Valid Length Of Amendment Document";
	public String appUpWetpetDocMSG = "Please Enter Valid Length Of Approved Uploaded WET/PET";
	public String wetPetTitleMSG = "Please Enter Valid Length Of WET/PET Title";
	public String itemEqpNoMSG = "Please Enter Valid Length Of Item/Eqpt Name";
	public String auth_amtMSG = "Please Enter Valid Length Of Authorization";
	
	
	public Boolean checkWepeLength(String we_pe_no) {
		if(!we_pe_no.equals("") && we_pe_no.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public Boolean checkAuth_amtLength(String auth_amt) {
		if(auth_amt.length() > 8)
		{
			return false;
		}
		if(!auth_amt.equals("") && auth_amt.contains(".")) {
			String[] auth_amt1= String.valueOf(auth_amt).split("\\.");
			if(auth_amt1[0].length() > 5 || auth_amt1[1].length() > 2)
			{
				return false;
			}
		}
		else {
			if(auth_amt.length() > 5)
			{	
				return false;
			}
		}
		return true;
    }
	
	public String amt_inc_decMSG = "Please Enter Valid Inc/Dec Authorization";
	public Boolean checkAmt_inc_decLength(String amt_inc_dec,int lenval) {
		if(amt_inc_dec.length() > lenval) {
			return false;
		}
		else {
			if(!amt_inc_dec.equals("") && amt_inc_dec.contains(".")) {
				String[] amt_inc_dec1= String.valueOf(amt_inc_dec).split("\\.");
				if(amt_inc_dec1[0].length() > 5 && amt_inc_dec1[1].length() > 2) 
				{
					return false;
				}
				else 
				{
					return true;
				}
			}
			else if(!amt_inc_dec.equals("") && amt_inc_dec.contains(".") && amt_inc_dec.contains("-")) {
				String[] amt_inc_dec1= String.valueOf(amt_inc_dec).split("\\.");
				if(amt_inc_dec1[1].length() > 5 && amt_inc_dec1[2].length() > 2) 
				{
					return false;
				}
				else 
				{
					return true;
				}
			}
			else 
			{
				return true;
			}
		}
    }
	
	public Boolean checkScenarioLength(String wepe) {
		if(!wepe.equals("") && wepe.length() > 15) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public boolean checkLocationLength(String location) {
		if(location.length() > 5) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	
	public boolean checkFormationLength(String Formation) {
		if(Formation.length() > 8) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkCategory_of_personnelLength(String  category_of_Personnel) {
		if(category_of_Personnel.length() != 1) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkMonthLength(String month) {
		if(month.length() > 2) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}

	public boolean checkWETypeLength(String we_type) {
		if(we_type.length() > 4) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkRemarksLength(String remarks) {
		if(remarks.length() > 255) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkSponsorDireLength(String sponsor_dire) {
		if(sponsor_dire.length() > 35) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
		
	}
	
	public boolean checkWepetabletittleLength(String table_title) {
		if(!table_title.equals("") && table_title.length() > 150) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkConditionLength(String condition) {
		if(!condition.equals("") && condition.length() > 255) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkMctLength(String mct_no) {
		 if(!mct_no.equals("") && mct_no.length() !=4)
         {
                 return false;
         }
         else 
         {
                 return true;
         }
	}
	
	public boolean checkModificationLength(String modification) {
		if(modification.length() >15) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkIncDecLength(String inc_dec) {
		if(inc_dec.length() >1) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkParent_codeLength(String parent_code) {
		if(parent_code.length() >3) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkLevel_in_hierarchyLength(String level_in_hierarchy) {
		if(level_in_hierarchy.length() >12) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	public boolean checkWetPetLength(String wet_pet_no) {
		if(wet_pet_no.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	
	public boolean checkBroadCategoryLength(String engr_stores_origin) {
		if(engr_stores_origin.length() > 30) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
//***************************************************END CUE VALIDATION ***************************************************************//


//***************************************************START TMS VALIDATION ***************************************************************//

	/*public String group_nameMSG = "PRF Gp Nomenclature Should Contain Maximum 35 Characters.";
	public Boolean group_nameLength(String group_name) {
		if(group_name.length() > 35) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
// Start Animal Validation	
		
	public String type_breedMSG = "Type of Breed Should Contain Maximum 50 Characters";
	public Boolean checkTypeBreedLength(String type_breed) {
		if(type_breed.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String army_numMSG = "Army Number Should Contain Maximum 20 Characters";
	public Boolean checkArmyNumLength(String army_num) {
		if(army_num.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String name_of_dogMSG = "Name of Dog Should Contain Maximum 35 Characters"; 
	public Boolean checkNameofDogLength(String name_of_dog) {
		if(name_of_dog.length() > 35) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String remount_noMSG = "Remount No Should Contain Maximum 8 Characters"; 
	public Boolean checkRemountNoLength(String remount_no) {
		if(remount_no.length() > 8) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String date_of_birthMSG = "Date of Birth Should Contain Maximum 10 Characters"; 
	public Boolean checkDateofBirthLength(String date_of_birth) {
		if(date_of_birth.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String ageMSG = "Age Should Contain Maximum 2 Digit"; 
	public Boolean checkageLength(String age) {
		if(age.length() > 2) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String details_of_sireMSG = "Details of Sire Should Contain Maximum 35 Characters"; 
	public Boolean checkDetailsofSireLength(String details_of_sire) {
		if(details_of_sire.length() > 35) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String details_of_damMSG = "Details of Dam Should Contain Maximum 35 Characters"; 
	public Boolean checkDetailsofDamLength(String details_of_dam) {
		if(details_of_dam.length() > 35) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String microchip_noMSG = "Microchip No Should Contain Maximum 20 Characters"; 
	public Boolean checkMicrochipNoLength(String microchip_no) {
		if(microchip_no.length() > 20) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String image_anmlMSG = "Photograph Should Contain Maximum 255 Characters";
	public Boolean checkImageAnmlLength(String fname) {
		if(fname.length() > 255) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	
	public String date_admittedMSG = "Date Admitted Should Contain Maximum 10 Characters"; 
	public Boolean checkDateAdmittedLength(String date_admitted) {
		if(date_admitted.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String fitness_deploymentMSG = "Fitness Deployment Should Contain Maximum 255 Characters"; 
	public Boolean checkFitnessDeploymentLength(String fitness_deployment) {
		if(fitness_deployment.length() > 255) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String animal_purchase_costMSG = "Animal Purchase Cost Should Contain Maximum 10 Digit"; 
	public Boolean checkAnimalPurchaseCostLength(String animal_purchase_cost) {
		if(animal_purchase_cost.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String animal_present_costMSG = "Animal Present Cost Should Contain Maximum 10 Digit"; 
	public Boolean checkAnimalPresentCostLength(String animal_present_cost) {
		if(animal_present_cost.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }*/
// End Animal Validation

//*************************************************** END TMS VALIDATION ***************************************************************//
	

//****************************  TMS  **************************************************************//
	
	// Start MCT Master TMS Validation		

	public String ro_type_of_hldgMSG = " Ro Type Of Holding Contain Maximum 4 Digits.";
	public String quantityMSG = " Qty Should Contain Maximum 4 Digits.";
	public String code_no_fromMSG = "MCT From Should Contain Maximum 4 Digits.";
	public Boolean code_no_fromLength(String code_no_from) {
		if(code_no_from.length() > 4) {
   	    	return false ;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String rangeMSG = "Range in Qty Should Contain Maximum 3 Digits.";
	public Boolean rangeLength(String range) {
		if(range.length() > 3) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String other_agencyMSG = "Other Agency Should Contain Maximum 35 Characters.";
	public String group_nameMSG = "PRF Gp Nomenclature Should Contain Maximum 35 Characters.";
	public Boolean group_nameLength(String group_name) {
		if(group_name.length() > 35) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String prf_codeMSG = "PRF Gp Should Contain Maximum 5 Digits.";
	public Boolean prf_codeLength(String prf_code) {
		if(prf_code.length() > 5) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	public String veh_catMSG = "Vehicle Category Should Contain Maximum 1 Character.";
	public String vehicle_class_codeMSG = "Vehicle Class Code Should Contain Maximum 1 Character.";
	public String class_of_vehicleMSG = " Class of Vehicle Should Contain Maximum 1 Character.";
	public Boolean vehicle_class_codeLength(String vehicle_class_code) {
		if(vehicle_class_code.length() > 1) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	//===============================TB_TMS_MCT_MAIN_MASTER==============================================//
	
	public String depot_codeMSG = "Depot Code Should Contain Maximum 1 Characters";
	public String type_of_vehMSG = "Type Of Vehicle Should Contain Maximum 1 Character.";
	public Boolean type_of_vehLength(String type_of_veh) {
		if(type_of_veh.length() > 1) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String year_of_entryMSG = " Yr of Entry into Service Should Contain 4 Digits.";
	public String mct_main_idMSG = "MCT Should Contain 4 Digits.";
	public Boolean mct_main_idLength(String mct_main_id) {
		if(mct_main_id.length() != 4) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String mct_nomenMSG = "MCT Nomenclature Should Contain Maximum 100 Character.";
	public Boolean mct_nomenLength(String mct_nomen) {
		if(mct_nomen.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	//===============================tb_tms_make_master==============================================//
	
	
	
	public String make_noMSG = "Make No Should Contain Maximum 3 Digits.";
	public String model_idMSG = "Model Should Contain Maximum 3 Digits.";
	public Boolean make_noLength(String make_no) {
		if(make_no.length() > 3) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String descriptionModelMSG = "Model Nomenclature Should Contain Maximum 100 Character.";
	public String descriptionMakeMSG = "Make Nomenclature Should Contain Maximum 100 Character.";
	public Boolean descriptionLength(String description) {
		if(description.length() > 100) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	//==================================TB_TMS_MODEL_MASTER=============================//


	/*public String descriptionModelMSG = "Model Nomenclature Should Contain Maximum 45 Character.";
	public Boolean descriptionModelLength(String description) {
		if(description.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }*/
	
	//========================================tb_tms_mct_master==========================//
	
	
	
	public String std_nomclatureTMSMSG = "Std Nomenclature Should Contain Maximum 300 Character.";
	public Boolean std_nomclatureTMSLength(String mct_nomen) {
		if(mct_nomen.length() > 300) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String auth_letter_noTMSMSG = "Auth Letter Should Contain Maximum 45 Character.";
	public Boolean auth_letter_noTMSLength(String auth_letter_no) {
		if(auth_letter_no.length() > 45) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String axle_wtsMSG = "Axle Weight Should Contain Maximum 5 Digit.";
	public String tonnageMSG = "Tonnage Should Contain Maximum 5 Digit.";
	public String towing_capacityMSG = "Towing Capacity Should Contain Maximum 5 Digit.";
	public String lift_capacityMSG = "Lift Capacity Should Contain Maximum 5 Digit.";
	public String yr_service_ffMSG = "Yr of Service (FF) Should Contain Maximum 5 Digit.";
	public String yr_service_nffMSG = "Yr of Service (NFF) Should Contain Maximum 5 Digit.";
	public Boolean axle_wtsLength(String axle_wts) {
		if(axle_wts.length() > 5) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String kms_runMSG = "Km Run Should Contain Maximum 10 Digit.";
	public String auth_letter_noReqMSG = "Auth Letter Should Contain Maximum 10 Character.";
	public String km_ffMSG = "Km for FF Should Contain Maximum 10 Character.";
	public String km_nffMSG = "Km for NFF Should Contain Maximum 10 Character.";
	public Boolean km_ffLength(String km_ff) {
		if(km_ff.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }

	public String mctMSG = "MCT Should Contain Maximum 10 Character.";
	public Boolean mctLength(String mct) {
		if(mct.length() != 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
// End MCT Master TMS Validation	
		
// BA No Allocation TMS Validation		
	//*****************************tb_tms_banum_req_child/tb_tms_banum_req_prnt*******************//

	public String issue_against_rioMSG = " Issue Against Should Contain Maximum 10 Character.";
	public String country_of_originTMSMSG = " Country of Origin Should Contain Maximum 10 Character.";
	public Boolean country_of_originTMSLength(String country_of_origin) {
		if(country_of_origin.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String engine_noMSG = "  Engine No Should Contain Maximum 20 Character.";
	public String chasis_noMSG = "  Chasis No Should Contain Maximum 20 Character.";
	public String initiating_authTMSMSG = "  Initiating Auth Should Contain Maximum 20 Character.";
	public Boolean initiating_authTMSLength(String initiating_auth) {
		if(initiating_auth.length() > 20) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String spl_eqpmnt_fitdMSG = "   Spl Eqpt Fitted Should Contain Maximum 50 Character.";
	public Boolean spl_eqpmnt_fitdLength(String spl_eqpmnt_fitd) {
		if(spl_eqpmnt_fitd.length() > 50) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String purchas_costMSG = "   Purchase Cost Should Contain Maximum 10 Character.";
	public Boolean purchas_costLength(String purchas_cost) {
		if(purchas_cost.length() > 10) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
    }
	
	public String type_of_reqMSG = "   Type of Request Should Contain Maximum 1 Character.";
	public Boolean type_of_reqLength(String type_of_req) {
		if(type_of_req.length() > 1) {
   	    	return false;
   	    }else {
   	    	return true;
   	    }
	}
	//**********************************tb_tms_banum_dirctry***********************************//
	
		public String em_noMSG = "   EM No Should Contain Maximum 10 Character.";
		public String ba_noMSG = "   BA No Should Contain Maximum 10 Character.";
		public Boolean ba_noLength(String ba_no) {
			if(ba_no.length() != 10) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }

		
	//**********************************tb_tms_convert_req********************************//
		
		public String convrsn_doneMSG = "   Conversion Mod Done Should Contain Maximum 5 Character.";
		public Boolean convrsn_doneLength(String convrsn_done) {
			if(convrsn_done.length() > 5) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
	
	//*************************************  Tms C Veh Validation ****************************//
	
		// Start C Veh 
		
		public String source_sus_noMSG = "Please Enter 8 Digit Source SUS No.";
		
		public String target_sus_noMSG = "Please Enter 8 Digit Target SUS No.";
		
		public String target_unit_nameMSG = "Target Unit Name Should Contain Maximum 100 Characters";
		
		public String source_unit_nameMSG = "Source Unit Name Should Contain Maximum 100 Characters";
		
		public String depot_sus_noMSG = "Please Enter 8 Digit Depot SUS No.";
		
		public String authority_noMSG = "Authority No Should Contain Maximum 50 Character.";
		
		public String drr_dir_ser_noMSG = "Drr Dir Ser No Should Contain Maximum 50 Character.";
		
		public String auction_amountMSG = "Auction Amount Should Contain Maximum 10 Digit.";
		public Boolean authority_noLength(String authority_no) {
			if(authority_no.length() > 50) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
			
			
		// End C Veh 
		
		
			
		///START Rel Order
		public String InmctMSG = "In Lieu MCT Should Contain Maximum 10 Character.";
		
		public String Ro_noMSG = "Ro No Should Contain Maximum 50 Character.";
		
		public Boolean ro_noLength(String ro_no) {
			
			if(ro_no.length() > 50) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		/////END Rel Order
			
		// Start Animal Validation	
		public String type_breedMSG = "Type of Breed Should Contain Maximum 50 Characters";
		
		public String type_splztnMSG = "Type of Specialisation Should Contain Maximum 50 Characters";
		
		public String sourceMSG = "Type of Source Should Contain Maximum 50 Characters";
		
		public String type_of_animalMSG = "Type of Animal Should Contain Maximum 50 Characters";
		
		public String authorityMSG = "Authority Should Contain Maximum 50 Characters";
		
		public Boolean checkAnimalMasterLength(String type) {
			if(type.length() > 50) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String type_dogMSG = "Type of Dog Should Contain Maximum 30 Characters";
		
		public String type_colorMSG = "Type of Color Should Contain Maximum 30 Characters";
		public Boolean check30Length(String length_30) {
			if(length_30.length() > 30) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String fitness_deploymentMSG = "Fitness Deployment Should Contain Maximum 255 Characters"; 
		public String std_nomclatureROMSG = "Std Nomclature Should Contain Maximum 255 Characters";
		public String disposal_remarksMSG = "Disposal Remarks Should Contain Maximum 255 Characters";
		
		public String awared_remarksMSG = "Award Remarks Should Contain Maximum 255 Characters";
		public String remarksMSGTMS = "Remarks Should Contain Maximum 255 Characters";
		public Boolean check255Length(String length_255) {
			if(length_255.length() > 255) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String fitness_statusMSG = "Fitness Status Should Contain Maximum 35 Characters";
		
		public String name_of_dogMSG = "Name of Dog Should Contain Maximum 35 Characters";
		
		public String details_of_sireMSG = "Details of Sire Should Contain Maximum 35 Characters";
		
		public String details_of_damMSG = "Details of Dam Should Contain Maximum 35 Characters";
		
		public Boolean check35Length(String length_35) {
			if(length_35.length() > 35) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String ue_of_dogsMSG = "UE of Dogs Should Contain Maximum 5 Digit";               
		
		public String ue_of_equinsMSG = "UE of Equines Should Contain Maximum 5 Digit";  
		
		public Boolean checkUELength(String ue) {
			if(ue.length() > 5) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		
		public String army_numMSG = "Army Number Should Contain Maximum 20 Characters";
		
		public String microchip_noMSG = "Microchip No Should Contain Maximum 20 Characters";
		
		public Boolean check20Length(String length_20) {
			if(length_20.length() > 20) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String remount_noMSG = "Remount No Should Contain Maximum 8 Characters"; 
		public Boolean checkRemountNoLength(String remount_no) {
			if(remount_no.length() > 8) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String dateMSG = "Date Should Contain Maximum 10 Characters";
		public String iv_dateMSG = "IV Date Should Contain Maximum 10 Characters";
		public String eng_expiry_dateMSG = "Eng Expire Date Should Contain Maximum 10 Characters";
		public Boolean checkDateLength(String date) {
			if(date.length() > 10) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		public String ageMSG = "Age Should Contain Maximum 2 Digit"; 
		public Boolean checkageLength(String age) {
			if(age.length() > 2) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
				
			
				public String upload_voucher1MSG = "Upload Voucher Should Contain Maximum 255 Characters";
				public String front_view_imageMSG = " Fornt View Image Should Contain Maximum 255 Characters";
				public String back_view_imageMSG = " Rear View Image Should Contain Maximum 255 Characters";
				public String side_view_imageMSG = " Left View Image Should Contain Maximum 255 Characters";
				public String top_view_imageMSG = " Right View Image Should Contain Maximum 255 Characters";
				public String engine_imageMSG = "  Pencil Rubbing of Engine Should Contain Maximum 255 Characters";
				public String chasis_imageMSG = "  Pencil Rubbing of Chassis Should Contain Maximum 255 Characters";
				public String image_anmlMSG = "Photograph Should Contain Maximum 255 Characters";
				public String image_MMSMSG = "Image/Pdf Should Contain Maximum 255 Characters";
				public String image_MSG = "Screen Shot/Pdf Should Contain Maximum 255 Characters";
				
				public Boolean checkImageAnmlLength(String fname) {
					if(fname.length() > 255) {
			   	    	return false;
			   	    }else {
			   	    	return true;
			   	    }
			    }
				
				public String animal_purchase_costMSG = "Animal Purchase Cost Should Contain Maximum 10 Digit"; 
				
				public String animal_present_costMSG = "Animal Present Cost Should Contain Maximum 10 Digit";
				
				public Boolean check10Length(String length_10) {
					if(length_10.length() > 10) {
			   	    	return false;
			   	    }else {
			   	    	return true;
			   	    }
			    }
				
			
			// End Animal Validation
			
//****************************************************END TMS ***********************************************************//
		
			
//***************************************************MMS***************************************************************//

//Start MMS Validation	

//****************MLCCS*************************************************//

		public String req_agencyMSG = "Inroducing Agency Should Contain Maximum 150 Characters"; 
		public Boolean checkReqAgencyLength(String req_agency)
		{
			if(req_agency.length() > 150) 
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		public String ref_noMSG = "Reference No Should Contain Maximum 50 Characters"; 
		public String cat_part_noMSG = "Cat/Part Should Contain Maximum 50 Characters"; 
		public String auth_lett_noMSG = "Auth/Letter No. Should Contain Maximum 50 Characters"; 
		public String nato_stk_noMSG = "NATO Stock No(NSN) Should Contain Maximum 50 Characters"; 
		public String def_cat_no_dcanMSG = "Def Catalogue No(DCAN) Should Contain Maximum 50 Characters";  
		public Boolean checkCatPartNoLength(String cat_part_no) 
		{
			if(cat_part_no.length() > 50)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		
		 
		public String brief_descMSG = "Brief Discription Should Contain Maximum 100 Characters"; 
		public String manuf_agencyMSG = "Manufacturing Agency Should Contain Maximum 100 Characters";
		public String ahsp_agencyMSG = "AHSP Should Contain Maximum 100 Characters"; 
		public String ro_commandMSG = "Command Should Contain Maximum 100 Characters"; 
		public Boolean checkNomenLength(String nomen)
		{
			if(nomen.length() > 100) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		
		
		public String cos_secMSG = "COS Section Should Contain Maximum 10 Characters"; 
		public String costMSG = "Cost Should Contain Maximum 10 Characters"; 
		public Boolean checkCosSecLength(String cos_sec) 
		{
		if(cos_sec.length() > 10)
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		
		
		public String census_noMSG = "Please Enter 9 Digit Census No"; 
		public Boolean checkCenSusnoLength(String census_no) 
		{
			if(census_no.length() != 9) 
			{
				return false;
			}
		else
		{
			return true;
		}
		}
		
		public String origin_countryMSG = "Country Should Contain Maximum 15 Characters"; 
		public Boolean checkOriginCountryLength(String origin_country) {
		if(origin_country.length() > 15) {
			return false;
		}
		else{
			return true;
		}
		}
		
		public String induc_yearMSG = "Year Should Contain Maximum 4 Characters"; 
		public Boolean checkInducYearLength(String induc_year) 
		{
			if(induc_year.length() > 4)
			{
				return false;
			}
			else
			{
			return true;
			}
		}
		
	
		public String ro_ueMSG = "Ro UE Should Contain Maximum 5 Characters"; 
		public String ro_uhMSG = "Ro UH Should Contain Maximum 5 Characters"; 
		public Boolean checkRoUeLength(String ro_ue) 
		{
			if(ro_ue.length() > 5) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		}

		
		
		public String type_of_stkMSG = "Stock Should Contain Maximum 1 Characters"; 
		public Boolean checkTypeOfStkLength(String type_of_stk) 
		{
			if(type_of_stk.length() > 1) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		
		public String ro_typeMSG = "RO Type Should Contain Maximum 2 Characters"; 
		public String ro_forMSG = "RO Against Should Contain Maximum 2 Characters"; 
		public String ro_type_of_issueMSG = "RO Type of Issue Should Contain Maximum 2 Characters"; 
		public String ro_type_issueMSG = "RO Type Of Issue Should Contain Maximum 2 Characters"; 
		public Boolean checkRoForLength(String ro_for)
		{
			if(ro_for.length() > 2) 
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		
		
		
		public String ro_agencyMSG = "RO Agency Should Contain Maximum 25 Characters"; 
		public String ro_noMSG = "RO No Should Contain Maximum 25 Characters"; 
		public String from_comd_sus_noMSG = "Comd SUS No Should Contain Maximum 25 Characters"; 
		public String rio_noMSG = "RIO No Should Contain Maximum 25 Characters"; 
		public Boolean checkfrom_comd_sus_noLength(String from_comd_sus_no) 
		{
			if(from_comd_sus_no.length() > 25) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
//****************END MLCCS*************************************************//
						
		//****************START UNIT HOLDING*************************************************//
		
		public String rv_noMSG = "IV No. Should Contain Maximum 25 Characters"; 
		public Boolean checkRv_NoLength(String rv_no) {
			if(rv_no.length() > 25) {
				return false;
				}else {
				return true;
			}
		}
		
		public String issued_qtyMSG = "Issued Qty. Should Contain Maximum 7 Digit"; 
		public Boolean checkIssued_QtyLength(String issued_qty) {
			if(issued_qty.length() > 7) {
				return false;
				}else {
				return true;
			}
		}
		
		public String eqpt_makeMSG = "Equipment Make. Should Contain Maximum 15 Characters"; 
		public Boolean checkEqpt_MakeLength(String eqpt_make) {
			if(eqpt_make.length() > 15) {
				return false;
				}else {
				return true;
			}
		}
		
		public String eqpt_modelMSG = "Equipment Model. Should Contain Maximum 15 Characters"; 
		public Boolean checkEqpt_ModelLength(String eqpt_model) {
			if(eqpt_model.length() > 15) {
				return false;
				}else {
				return true;
			}
		}
		
		public String Unit_PriceMSG = "Unit price. Should Contain Maximum 12 Character."; 
		public Boolean checkUnit_PriceLength(String unit_price) {
			if(unit_price.length() > 12) {
				return false;
				}else {
				return true;
			}
		}
		
		public String Depres_RateMSG = "Depres Rate. Should Contain Maximum 7 Character."; 
		public Boolean checkDepres_RateLength(String depres_rate) {
			if(depres_rate.length() > 7) {
				return false;
				}else {
				return true;
			}
		}
		
		public String life_of_assetsMSG = "Life Of Assets. Should Contain Maximum 10 Digit."; 
		public Boolean checkLife_Of_AssetsLength(String life_of_assets) {
			if(life_of_assets.length() > 10) {
				return false;
				}else {
				return true;
			}
		}
		
		public String eqpt_regn_noMSG = "Equipment Register No. Should Contain Maximum 25 Digit."; 
		public Boolean checkEqpt_Regn_NoLength(String eqpt_regn_no) {
			if(eqpt_regn_no.length() > 25) {
				return false;
				}else {
				return true;
			}
		}
		
		public String Barrel1_DetlMSG = "Barrel 1 Details. Should Contain Maximum 150 Character."; 
		public Boolean checkBarrel1_DetlLength(String barrel1_detl) {
			if(barrel1_detl.length() > 150) {
				return false;
				}else {
				return true;
			}
		}
		
		public String Barrel2_DetlMSG = "Barrel 2 Details. Should Contain Maximum 150 Character."; 
		public Boolean checkBarrel2_DetlLength(String barrel2_detl) {
			if(barrel2_detl.length() > 150) {
				return false;
				}else {
				return true;
			}
		}
		
		public String Barrel3_DetlMSG = "Barrel 3 Details. Should Contain Maximum 150 Character."; 
		public Boolean checkBarrel3_DetlLength(String barrel3_detl) {
			if(barrel3_detl.length() > 150) {
				return false;
				}else {
				return true;
			}
		}
		
		public String Barrel4_DetlMSG = "Barrel 4 Details. Should Contain Maximum 150 Character."; 
		public Boolean checkBarrel4_DetlLength(String barrel4_detl) {
			if(barrel4_detl.length() > 150) {
				return false;
				}else {
				return true;
			}
		}
		
		
//****************END UNIT HOLDING*************************************************//		
		
//****************START MMS DOMAIN MASTER*************************************************//	
		
		public String CodeValueMSG = "Code Value. Should Contain Maximum 3 Digit."; 
		public Boolean checkCodeValueLength(String codevalue) {
			if(codevalue.length() > 4) {
				return false;
				}else {
				return true;
			}
		}
		
		public String typeofhldgMSG = "Type Of Holding. Should Contain Maximum 150 Character.";
		public String typeofeqptMSG = "Type Of Eqpt. Should Contain Maximum 150 Character.";
		public String labelMSG = "Label Should Contain Maximum 150 Character."; 
		public Boolean checklabelLength(String label) {
			if(label.length() > 150) {
				return false;
				}else {
				return true;
			}
		}
		public String domainMSG = "Domain Name Should Contain Maximum 15 Character."; 
		public Boolean checkdomainnameLength(String domainid) {
			if(domainid.length() > 15) {
				return false;
				}else {
				return true;
			}
		}
		
		public String label_sMSG = "Label in Short  Should Contain Maximum 6 Character."; 
		public Boolean checklabel_sLength(String label_s) {
			if(label_s.length() > 6) {
				return false;
				}else {
				return true;
			}
		}
		
		public String disp_orderMSG = "Disp Order  Should Contain Maximum 5 Character."; 
		public Boolean checkdisporderLength(String disp_order) {
			if(disp_order.length() > 5) {
				return false;
				}else {
				return true;
			}
		}
		
//****************END DOMAIN MASTER*************************************************//
	     public String iv_noMSG = "IV No Should Contain Maximum 50 Characters"; 
			public Boolean checkIvNoLength(String iv_no) {
				if(iv_no.length() > 50) {
					return false;
					}else {
					return true;
				}
			}
			
			
			public String govt_letter_noMSG = "Govt Letter No Should Contain Maximum 50 Characters"; 
			public Boolean checkGovtLetterNoLength(String govt_letter_no) {
				if(govt_letter_no.length() > 50) {
					return false;
					}else {
					return true;
				}
			}
			
		
		public String qtyMSG = "Range in Qty Should Contain Maximum 4 Digits.";

		public Boolean qtyLength(String qty) {
			if (qty.length() > 5) {
				return false; 
			} else {
				return true;
			}
		}
		 
		public String upload_auth_letterMSG = "Upload Auth Letter Should Contain Maximum 255 Characters";

		
		public String spl_remarksMSG = "Remarks Should Contain Maximum 200 Characters"; 
		public Boolean checkSplRemarksLength(String spl_remarks) 
		{
			if(spl_remarks.length() > 200)
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		
		public String prfMSG = "PRF Code Should Contain Maximum 8 Characters"; 
		public Boolean checkPrf_CodeLength(String prf_code) {
			if(prf_code.length() > 8) {
				return false;
				}else {
				return true;
			}
		}
		public String nomenMSGMMS = "Nomenclature Should Contain Maximum 255 Characters";
		public String Regn_Seq_NoMSG = "Register No. Should Contain Maximum 20 Digit."; 
		public Boolean checkRegn_Seq_NoLength(String regn_seq_no) {
			if(regn_seq_no.length() > 20) {
				return false;
				}else {
				return true;
			}
		}
						
	// End MMS Validation\
	//***************************************************END MMS***************************************************************//	
	
	
	
//***************************************************START HELPDESK***************************************************************//	
    public String DescriptionLengthHelpdeskMSG = "Description Should Contain Maximum 1000 Character.";
    public boolean checkDescriptionLengthHelpdeskLength(String description) {
        if(description.length() >1000) {
               return false;
       }else {
               return true;
       }
    }
 
    public String Issue_summaryHelpdeskMSG = "Issue Summary Should Contain Maximum 100 Character.";
    public boolean checkIssue_summaryHelpdeskLength(String issue_summary) {
        if(issue_summary.length() >100) {
               return false;
       }else {
               return true;
       }
    }
    
    public String agent_nameHelpdeskMSG = "Agent Name Should Contain Maximum 30 Character.";
    public boolean checkagent_nameHelpdeskLength(String agent_name) {
        if(agent_name.length() >30) {
               return false;
       }else {
               return true;
       }
    }
    
    public String sectionHelpdeskMSG = "Section Should Contain Maximum 20 Character.";
    public boolean checksectionHelpdeskLength(String section) {
        if(section.length() >20) {
               return false;
       }else {
               return true;
       }
    }
    
    public String questionHelpdeskMSG = "Question Should Contain Maximum 255 Character.";
    public boolean checkquestionHelpdeskLength(String question) {
        if(question.length() >255) {
               return false;
       }else {
               return true;
       }
    }
    
    public String msgMarqueeMSG = "MSG Should Contain Maximum 200 Character.";
    public boolean checkmsgMarqueeLength(String msg) {
        if(msg.length() >200) {
               return false;
       }else {
               return true;
       }
    }
  //***************************************************END HELPDESK***************************************************************//
    
//***************************************************START LOGIN***************************************************************//
    
    public String ModuleNameMSG = "Module Name Should Contain Maximum 20 Character.";
    public boolean ModuleNameLength(String module_name) {
        if(module_name.length() >20) {
               return false;
       }else {
               return true;
       }
    }
    
    
    
    public String SubModuleNameMSG = "Sub Module Name Should Contain Maximum 255 Character.";
    public boolean SubModuleNameLength(String submodule_name) {
        if(submodule_name.length() >255) {
               return false;
       }else {
               return true;
       }
    }
    
    
    public String ScreenNameMSG = "Screen Name Should Contain Maximum 80 Character.";
    public boolean ScreenNameLength(String screen_name) {
        if(screen_name.length() >80) {
               return false;
       }else {
               return true;
       }
    }
    
    public String ScreenURLMSG = "Screen Url Should Contain Maximum 125 Character.";
    public boolean ScreenURLLength(String screen_url) {
        if(screen_url.length() >125) {
               return false;
       }else {
               return true;
       }
    }
    
    public String RoleMSG = "Role Should Contain Maximum 45 Character.";
    public boolean RoleLength(String role) {
        if(role.length() >45) {
               return false;
       }else {
               return true;
       }
    }    
    
    public String LoginNameMSG = "Login Name Should Contain Maximum 70 Character.";
    public boolean LoginNameLength(String login_name) {
        if(login_name.length() > 70) {
               return false;
       }else {
               return true;
       }
    }
    
    public String UserNameMSG = "Name Should Contain Maximum 30 Character.";
    public boolean UserNameLength(String user_name) {
        if(user_name.length() > 30) {
               return false;
       }else {
               return true;
       }
    }
    
    public String ArmyNoMSG = "Army No Should Contain Maximum 12 Character.";
    public boolean ArmyNoLength(String army_no) {
        if(army_no.length() > 12) {
               return false;
       }else {
               return true;
       }
    }
    
    public String CellNumberMsg = "Cell Number Should Contain Maximum 30 Character.";
    public boolean CellNoLength(String cell_no) {
        if(cell_no.length() > 30) {
               return false;
       }else {
               return true;
       }
    }
    
    
    public String PasswordMSG = "Password Should Contain 8 to 28 Character.";
    public boolean PasswordLength(String password) {
        if(password.length() < 8 & password.length() > 28) {
               return false;
       }else {
               return true;
       }
    }
    
    public String PasswordPatternMSG = "Password Should Contain at least one number and one uppercase and lowercase letter and special character";
    //***************************************************END LOGIN***************************************************************//
    
    
    
    
/////////////////////////////// Comman Validation /////////////////////

public String isValidMobileNoMSG = "Mobile number must contain Only 10 digit";
public boolean isValidMobileNo(String mobNumber) {
if (mobNumber.matches("\\d{10}")) {
return true;
} else {
return false;
}
}

public String isOnlyAlphabetMSG = "Must Be Contain Only Alphabet";
public boolean isOnlyAlphabet(String str) {
if (str.matches("^[a-zA-Z\\s]*$")) {
return true;
} else {
return false;
}
}

public String isOnlyAlphabetNumericMSG = "Must Be Contain Only Numbers & Alphabet";
public boolean isOnlyAlphabetNumeric(String str) {
if (str.matches("^[0-9a-zA-Z\\s\\-\\_\\[\\]\\/]*$")) {
return true;
} else {
return false;
}
}

public String isOnlyNumerMSG = "Must Be Contain Only Digit";
public boolean isOnlyNumer(String str) {
if (str.matches(".*[^0-9].*")) {
return true;
} else {
return false;
}
}

public String isValidAadharMSG = "Aadhar must contain Only 12 digit";
public boolean isValidAadhar(String str) {
if (str.matches("\\d{12}")) {
return true;
} else {
return false;
}
}

public String isValidPanMSG = "Please Enter Valid PAN";
public boolean isValidPan(String panCardNo) {
String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
panCardNo = panCardNo.toUpperCase();
Pattern p = Pattern.compile(regex);
Matcher m = p.matcher(panCardNo);
return m.matches();
}

public String PanNoMSG = "Pan No Should Contain Maximum 10 Character.";
public boolean PanNoLength(String pan) {
if (pan.length() > 10) {
return false;
} else {
return true;
}
}

public String isValidDateMSG = "Please Enter Valid Date";

public boolean isValidDate(String date) {
SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
if (date == null || date.equals("") || date.equals("DD/MM/YYYY")) {
return false;
} else {
try {
format.parse(date);
return true;
} catch (ParseException e) {
return false;
}
}
}
public String isValidEmailMSG = "Please Enter Valid ";
public boolean isValidEmail(String email)
{
String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$";
        
Pattern pat = Pattern.compile(emailRegex);
if (email == null)
return false;
return pat.matcher(email).matches();
}

public String removeExtraSpaceFromString(String str) {
return str.replaceAll("\\s+", " ").trim();

}

public String isValidAuthMSG = "Please Enter Valid ";
public boolean isValidAuth(String auth) {	
String reg = "^[0-9a-zA-Z\\s\\-\\_\\[\\]\\/]*$";
auth = auth.toUpperCase();
Pattern p = Pattern.compile(reg);
Matcher m = p.matcher(auth);
return m.matches();
}

public int nameMax = 50;
public int nameMin = 1;
public int authorityMax = 100;
public int authorityMin = 1;
public int remarkMax = 255;
public int remarkMin = 1;
public int cadet_noMax = 7;
public int cadet_noMin = 1;
public int id_card_noMax = 10;
public int id_card_noMin = 1;
public int pin_noMax = 6;
public int pin_noMin = 1;
public int gmail_Max = 64;
public int gmail_Min = 1;
public int card_no_max = 25;
public int card_no_min = 1;

public String isValidLengthMSG = "";
public boolean isvalidLength(String str,int max,int min) {
isValidLengthMSG = "";
int len=str.length();
if(min!=0 && min!=1) {
if(len<min || len>max) {
isValidLengthMSG ="Should Contain "+min+" to "+max+" Character";
return false;
}
else {
return true;
}
}
else {
if(len>max) {
isValidLengthMSG ="Should Contain Maximum "+max+" Character";
return false;
}
else {
return true;
}
}
}


public String SusNoMSG = "SUS No Should Contain Maximum 8 Character.";
public boolean SusNoLength(String sus_no) {
if (sus_no.length() != 8) {
return false;
} else {
return true;
}
}


public String isOnlyAlphabetNumericSpaceNotMSG = "Special Charater and Space Not Allowed in ";
public boolean isOnlyAlphabetNumericSpaceNot(String str) {
if (str.matches("^[0-9a-zA-Z]*$")) {
return true;
} else {
return false;
}
}

public String YearMSG = "Year Should Contain Maximum 4 Character.";
public boolean YearLength(String year_of_comm) {
if (year_of_comm.length() != 4) {
return false;
} else {
return true;
}
}

public String isUnitMSG = "Must Be Contain Only Numbers & Alphabet";
public boolean isUnit(String str) {
if (str.matches("^[0-9a-zA-Z\\s\\-\\_\\[\\]\\/]*$")) {
return true;
} else {
return false;
}
}
    
    
public String ModelNumber = "Model Number Should be Maximum 20 Characters";
	public Boolean ModelNumberLength(String ModelNumber) {
		if(ModelNumber.length() > 20) {
  	    	return false;
  	    }else {
  	    	return true;
  	    }
   }
	
	 public String MachineNumber = "Machine Number Should be Maximum 35 Characters";
		public Boolean MachineNumberLength(String MachineNumber) {
			if(MachineNumber.length() > 35) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    } 

    
		public String validateSlashMSG = "Only slash(/) allowed in";
		public boolean validateSlash(String str) {
			if (str.matches("^[0-9a-zA-Z\\/]*$")) {
				return true;
			} else {
				return false;
			}
		} 
    
		public String validateunderscoreMSG = "Only Underscore(_) and dash allowed in";
		public boolean validateunderscore(String str) {
			if (str.matches("^[0-9a-zA-Z\\_-]*$")) {
				return true;
			} else {
				return false;
			}
		}
    ///---------------------------------Avation  
		
		
		public String abc_codeMSG = "ABC Gp Should Contain Maximum 5 Digits.";
		public Boolean abc_codeLength(String abc_code) {
			if(abc_code.length() > 5) {
	   	    	return false;
	   	    }else {
	   	    	return true;
	   	    }
	    }
		
		
		public String act_nomenMSG = "ACT Nomenclature Should Contain Maximum 100 Character.";
		public Boolean act_nomenLength(String act_nomen) {
		if(act_nomen.length() > 100) {
		          return false;
		          }else {
		          return true;
		          }
		      }

		public String year_of_entryAviationMSG = " Yr of Entry into Service Should Contain 4 Digits.";
		public String act_main_idMSG = "ACT Should Contain 4 Digits.";
		public Boolean act_main_idLength(String act_main_id) {
		if(act_main_id.length() != 4) {
		          return false;
		          }else {
		          return true;
		          }
		      }
		
}