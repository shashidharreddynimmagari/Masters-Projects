
#Command to clean environment using rm command
rm(list=ls())
# Set working directory to change present working path
setwd("C:/Code_Repo/dany/assignment")

# Install and load required libraries
#install.packages("dplyr")
library(dplyr)

# List all the files in the current working directory
dir()

# Read show data files using read.table function
shower_data <- read.table("Shower_data.csv.xls",sep=";",header = 1,dec=",")
survey_data <- read.table("Shower_survey_data.csv.xls",sep=";",header = 1,dec = ",")



# Function to calculated energy based on given formula
shower_data$Energy <- shower_data$Volume*((shower_data$Avgtemperature-12)/0.65)*(4.184/3600)

# a) to check whether real-time feedback significantly decreases the average energy consumption per shower. 
## Hypotheses
# H0**: Real-time feedback significantly decreases energy consumption per shower?
# H1**: Real-time feedback significantly increases energy consumption per shower? 

# split in Baseline und no Baseline Phase
data_with_baseline <- shower_data %>%dplyr::filter(Shower <= 10)
data_no_baseline <- shower_data %>%dplyr::filter(Shower > 10)

# built two dataframes: First dataframe = Control group, Second dataframe = Experimental group
data_control <- data_no_baseline %>% filter(Showertime <= 20)
data_feedback <- data_no_baseline %>% filter(Showertime > 20)

# group by Hh_id for control and feedback group
data_control_Energy <- data_control %>% group_by(Shower) %>%
  summarise(SumEnergy = sum(Energy),
            avgEnergy = mean(Energy))

data_feedback_Energy <- data_feedback %>% group_by(Shower) %>%
  summarise(SumEnergy = sum(Energy),
            avgEnergy = mean(Energy))

# two-sample and two-sided t-test of average energy of each shower in the according showertime
t.test(data_control_Energy$avgEnergy, data_feedback_Energy$avgEnergy, mu = 0, conf.level = .95)

# Hence Reject Null Hypothesis

## b) Do young people consume less Energy ##
# Hypotheses:
# H0: People aged 20-29 do not consume less than older people during the baseline phase
# H1: People aged 20-29 do consume less than older people during the baseline phase

# combine the Shower- and Survey-dataset by a left_join

# split in Baseline und no Baseline Phase
data_with_baseline <- shower_data %>%dplyr::filter(Shower <= 10)
#data_no_baseline <- data %>%dplyr::filter(Shower > 10)
combined_dataset <- dplyr::left_join(data_with_baseline, survey_data)

# use this dataset and group it by Hh_ID, age, then summarise the average Volume
age_participants <- combined_dataset %>% dplyr::group_by(Hh_ID, alter) %>% 
  dplyr::summarise(avgEnergy = mean(Energy))

# filter dataset by people aged 20-29 and older and save it in according new dataset
age_20_29 <- age_participants%>% dplyr::filter(alter == "20-29")

age_older_20_29 <- age_participants%>%filter(alter != "20-29")
#age_20_29$avgEnergy
#two-sample and two-sided t-test of people aged 20-29 and older and compare their average Volume
t.test(age_20_29$avgEnergy, age_older_20_29$avgEnergy, mu = 0, conf.level = .95)

rm(age_older_20_29,age_20_29)

# Reject Null Hypothesis

## c) Does gender influence water consumption ##
# Hypotheses
# H0: Gender of participants does not influence the average shower volume during the baseline phase
# H1: Gender of participants does influence the average shower volume during the baseline phase

# use combined dataset and compute average Volume for every Household and the according gender
gender <- combined_dataset %>% group_by(Hh_ID, gesl) %>% summarise(avgVolume=mean(Volume)) 

# filter the new dataset gender by sexuality (männlich & is not männlich = weiblich)
weiblich <- gender%>% filter(gesl == "weiblich") 
mannlich <- gender%>%filter(gesl != "weiblich")

#two-sample & two-sided t-test, compare the average volume of each gender and the according Household ID's on a confidence level of 95 %
t.test(mannlich$avgVolume, weiblich$avgVolume, mu = 0, conf.level = .95)


# We dont have enough confidence to reject null hypothesis,though means are not equal

#######################################################################

## d) Do participants with an income < 9000 Fr show similar water consumption changes than the ones with an income >= 9000 ##
# Hypotheses
# H0: participants with an income <9000 show similar water consumption changes than the ones with a higher income
# H1: participants with an income <9000 do not show similar water consumption changes than the ones with a higher income

#


combined_dataset_no_baseline <- dplyr::left_join(data_no_baseline, survey_data)

c_d_baseline <- combined_dataset %>% dplyr::group_by(Hh_ID, einkommen) %>% 
  summarise(avgVolume = mean(Volume))
c_d_no_baseline <- combined_dataset_no_baseline %>% group_by(Hh_ID, einkommen) %>% 
  summarise(avgVolume = mean(Volume))

cd_bllower_9000 <- c_d_baseline %>% filter(einkommen %in% c("< 8000 Fr.", "8000 - 8999 Fr."))
cd_blabove_9000 <- c_d_baseline %>% filter(einkommen %in% c("10000 - 11999 Fr.", "12000 - 14999 Fr.", "9000 - 9999 Fr.",
                                                            "Mehr als 15000 Fr."))
na.omit(cd_bllower_9000)
na.omit(cd_blabove_9000)

cd_Iplower9000 <- c_d_no_baseline %>% filter(einkommen %in% c("< 8000 Fr.", "8000 - 8999 Fr."))
cd_Ipabove9000 <- c_d_no_baseline %>% filter(einkommen %in% c("10000 - 11999 Fr.", "12000 - 14999 Fr.",  "9000 - 9999 Fr.",
                                                              "Mehr als 15000 Fr."))

comparison_bl_above <- c(cd_blabove_9000$avgVolume - cd_Ipabove9000$avgVolume)

diff_Volume_above <- cd_Ipabove9000
diff_Volume_above$Avarage_BL <- cd_blabove_9000$avgVolume
diff_Volume_above$diff <- c(comparison_bl_above)

# Setting names 
names(diff_Volume_above) <- c("Hh_ID", "einkommen", "Avarage_volume_IP", "Avarage_volume_BL", "diff_above")


comparison_below <- cd_bllower_9000$avgVolume - cd_Iplower9000$avgVolume


diff_volume_Below <- cd_bllower_9000
diff_volume_Below$Avarage_IP <- cd_Iplower9000$avgVolume 
diff_volume_Below$diff <- c(comparison_below)

# Setting names
names(diff_volume_Below) <- c("Hh_ID", "einkommen", "Avarage_volume_BL", "Avarage_volume_IP", "diff_below")


t.test(diff_volume_Below$diff_below, diff_Volume_above$diff_above, mu = 0, conf.level = .95)

# Reject Null Hypothesis

###############################################################################

## e) What is the preferred strategy for saving the most energy ? ##

data_with_baseline <- shower_data %>%dplyr::filter(Shower <= 10)
data_no_baseline <- shower_data %>%dplyr::filter(Shower > 10)

#data_control_IP_no <- data_no_baseline %>% filter(group <= 2)

data_feedback_group <- shower_data %>% filter(group > 2) %>% group_by(Hh_ID, Shower <= 10) %>%
  summarise(avgShower = mean(Showertime),
            avgTemp = mean(Avgtemperature),
            avgFlowrate =mean(Flowrate),
            avgBreaktime = mean(Breaktime))
#avgShower
#data_feedback_group

data_feedback_group_1 <- data_feedback_group %>% filter(`Shower <= 10`== FALSE) 
data_feedback_group_2 <- data_feedback_group %>% filter(`Shower <= 10` == TRUE)

# combine datasets

b <- right_join(data_feedback_group_1, data_feedback_group_2, by = "Hh_ID")

dim(b)
#get the differences of the phases

attach(b)
diff_showertime <- avgShower.x - avgShower.y
diff_Breaktime <- avgBreaktime.x - avgBreaktime.y
diff_flowrate <- avgFlowrate.x - avgFlowrate.y
diff_temperature <- avgTemp.x - avgTemp.y
detach(b)

#diff_showertime
#get the differences of the phases
dim(data_feedback_group)
data_feedback_group_1$diff_showertime <- diff_showertime
data_feedback_group_1$diff_Breaktime <- diff_Breaktime
data_feedback_group_1$diff_flowrate <- diff_flowrate
data_feedback_group_1$diff_temperature <- diff_temperature
#
data_feedback_group_2$diff_showertime <- diff_showertime
data_feedback_group_2$diff_Breaktime <- diff_Breaktime
data_feedback_group_2$diff_flowrate <- diff_flowrate
data_feedback_group_2$diff_temperature <- diff_temperature

data_feedback_group = rbind(data_feedback_group_1,data_feedback_group_2)


avgShower_boolean <- case_when(data_feedback_group[,7] < 0 ~ TRUE,
                               data_feedback_group[,7] >= 0 ~ FALSE)
avgBreaktime_boolean <- case_when(data_feedback_group[,8] < 0 ~ FALSE,
                                  data_feedback_group[,8] >= 0 ~ TRUE)
avgFlowrate_boolean <- case_when(data_feedback_group[,9] < 0 ~ TRUE,
                                 data_feedback_group[,9] >= 0 ~ FALSE)
avgTemperature_boolean <- case_when(data_feedback_group[,10] < 0 ~ TRUE,
                                    data_feedback_group[,10] >= 0 ~ FALSE)

windows()
layout(matrix(1:4,nrow=2,byrow=TRUE))
barplot(table(avgBreaktime_boolean), col = "orchid3", main = "Avarage Breaktime")
barplot(table(avgTemperature_boolean), col = "cornflowerblue", main = "Avarage Temperature")
barplot(table(avgFlowrate_boolean), col = "tomato", main = "Avarage Flowrate")
barplot(table(avgShower_boolean), col = "forestgreen", main = "Avarage Showertime")

