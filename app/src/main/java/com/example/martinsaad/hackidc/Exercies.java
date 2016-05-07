package com.example.martinsaad.hackidc;

import com.google.gson.annotations.SerializedName;

/**
 * Created by martinsaad on 07/05/2016.
 */
public class Exercies {
    private String id;
    @SerializedName("training_plan")
    private String trainingPlan;
    @SerializedName("latest_exerciese_progress")
    private LatestExercieseProgress latestExercieseProgress;
    @SerializedName("exercise_instance")
    private ExerciseInstance exerciseInstance;
    @SerializedName("breaks_between_exercieses")
    private float breaksBetweenExercieses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainingPlan() {
        return trainingPlan;
    }

    public void setTrainingPlan(String trainingPlan) {
        this.trainingPlan = trainingPlan;
    }

    public LatestExercieseProgress getLatestExercieseProgress() {
        return latestExercieseProgress;
    }

    public void setLatestExercieseProgress(LatestExercieseProgress latestExercieseProgress) {
        this.latestExercieseProgress = latestExercieseProgress;
    }

    public ExerciseInstance getExerciseInstance() {
        return exerciseInstance;
    }

    public void setExerciseInstance(ExerciseInstance exerciseInstance) {
        this.exerciseInstance = exerciseInstance;
    }

    public float getBreaksBetweenExercieses() {
        return breaksBetweenExercieses;
    }

    public void setBreaksBetweenExercieses(float breaksBetweenExercieses) {
        this.breaksBetweenExercieses = breaksBetweenExercieses;
    }

    public String getTrainerNotes() {
        return trainerNotes;
    }

    public void setTrainerNotes(String trainerNotes) {
        this.trainerNotes = trainerNotes;
    }

    public String getTaineeNotes() {
        return taineeNotes;
    }

    public void setTaineeNotes(String taineeNotes) {
        this.taineeNotes = taineeNotes;
    }

    @SerializedName("trainer_notes")
    private String trainerNotes;
    @SerializedName("tainee_notes")
    private String taineeNotes;

    public class LatestExercieseProgress{
        private String id;
        private String date;
        private int sets;
        private String weight;
        @SerializedName("breaks_between_sets")
        private float breaksBetweenSets;
        @SerializedName("training_plan_exercise_detail")
        private String trainingPlanExerciseDetail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getSets() {
            return sets;
        }

        public void setSets(int sets) {
            this.sets = sets;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public float getBreaksBetweenSets() {
            return breaksBetweenSets;
        }

        public void setBreaksBetweenSets(float breaksBetweenSets) {
            this.breaksBetweenSets = breaksBetweenSets;
        }

        public String getTrainingPlanExerciseDetail() {
            return trainingPlanExerciseDetail;
        }

        public void setTrainingPlanExerciseDetail(String trainingPlanExerciseDetail) {
            this.trainingPlanExerciseDetail = trainingPlanExerciseDetail;
        }
    }

    public class ExerciseInstance{
        private String id;
        @SerializedName("gym_machine")
        private GymMachine gymMachine;
        private String name;
        private String muscle;
        private String level;
        private String description;
        private String videoUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public GymMachine getGymMachine() {
            return gymMachine;
        }

        public void setGymMachine(GymMachine gymMachine) {
            this.gymMachine = gymMachine;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMuscle() {
            return muscle;
        }

        public void setMuscle(String muscle) {
            this.muscle = muscle;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
    }

    public class GymMachine{
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }
}