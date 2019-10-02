package main.java.ui;

import main.java.items.Properties;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
public class PropertiesPanel extends JPanel implements ChangeListener{
    private static final long serialVersionUID = -7792138540825581130L;

    private static final int ANIMATION_BOOST_MAX = 100;
    private static final int ANIMATION_BOOST_MIN = 0;
    private static final String ANIMATION_BOOST_NAME = "Animation Boost: ";
    private static final String PANEL_NAME = "Parameters";
    private static final int ELEVATOR_CAPACITY_MAX = 9999;
    private static final int ELEVATOR_CAPACITY_MIN = 1;
    private static final String ELEVATOR_CAPACITY_NAME = "ElevatorContainer";
    private static final int ELEVATOR_CAPACITY_STEP = 1;
    private static final int PASSENGERS_NUMBER_MAX = 9999;
    private static final int PASSENGERS_NUMBER_MIN = 1;
    private static final String PASSENGERS_NUMBER_NAME = "Passengers number";
    private static final int PASSENGERS_NUMBER_STEP = 1;
    private static final int STORIES_NUMBER_MAX = 9999;
    private static final int STORIES_NUMBER_MIN = 2;
    private static final String STORIES_NUMBER_NAME = "Stories number";
    private static final int STORIES_NUMBER_STEP = 1;

    private JSpinner jSpinnerStoriesNumber;
    private JSpinner jSpinnerElevatorCapacity;
    private JSpinner jSpinnerPassengersNumber;
    private JSlider jSliderAnimationBoost;
    private JLabel jLabelAnimationBoost;


    public PropertiesPanel(Properties properties){
        super();

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.ipadx = UIDimensions.CONTROLS_PADDING_X;
        gridBagConstraints.weightx = UIDimensions.CONTROLS_DISTRIBUTE_X_SPACE;
        setLayout(gridBagLayout);
        setBorder(BorderFactory.createEtchedBorder());

        JLabel jLabelParameters = new JLabel(PANEL_NAME);
        gridBagConstraints.gridx = UIDimensions.LABEL_PARAMETERS_X;
        gridBagConstraints.gridy = UIDimensions.LABEL_PARAMETERS_Y;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = UIDimensions.LABEL_PARAMETERS_PADDING;
        gridBagLayout.setConstraints(jLabelParameters, gridBagConstraints);
        add(jLabelParameters);

        JLabel jLabelStoriesNumber = new JLabel(STORIES_NUMBER_NAME, SwingConstants.RIGHT);
        SpinnerNumberModel spinnerNumberModelStoriesNumber =
                new SpinnerNumberModel(properties.getStoriesNumber(), STORIES_NUMBER_MIN,
                        STORIES_NUMBER_MAX, STORIES_NUMBER_STEP);
        jSpinnerStoriesNumber = new JSpinner(spinnerNumberModelStoriesNumber);
        gridBagConstraints.insets = UIDimensions.SPINNER_NUMBER_PADDING;
        gridBagConstraints.gridx = UIDimensions.STORIES_NUMBER_LABEL_X;
        gridBagConstraints.gridy = UIDimensions.STORIES_NUMBER_Y;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jLabelStoriesNumber, gridBagConstraints);
        gridBagConstraints.gridx = UIDimensions.STORIES_NUMBER_X;
        gridBagConstraints.gridy = UIDimensions.STORIES_NUMBER_Y;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(jSpinnerStoriesNumber, gridBagConstraints);
        add(jLabelStoriesNumber);
        add(jSpinnerStoriesNumber);

        JLabel jLabelElevatorCapacity = new JLabel(ELEVATOR_CAPACITY_NAME, SwingConstants.RIGHT);
        SpinnerNumberModel spinnerNumberModelElevatorCapacity =
                new SpinnerNumberModel(properties.getElevatorCapacity(), ELEVATOR_CAPACITY_MIN,
                        ELEVATOR_CAPACITY_MAX, ELEVATOR_CAPACITY_STEP);
        jSpinnerElevatorCapacity = new JSpinner(spinnerNumberModelElevatorCapacity);
        gridBagConstraints.gridx = UIDimensions.ELEVATOR_CAPACITY_LABEL_X;
        gridBagConstraints.gridy = UIDimensions.ELEVATOR_CAPACITY_Y;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jLabelElevatorCapacity, gridBagConstraints);
        gridBagConstraints.gridx = UIDimensions.ELEVATOR_CAPACITY_X;
        gridBagConstraints.gridy = UIDimensions.ELEVATOR_CAPACITY_Y;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(jSpinnerElevatorCapacity, gridBagConstraints);
        add(jLabelElevatorCapacity);
        add(jSpinnerElevatorCapacity);

        JLabel jLabelPassengersNumber = new JLabel(PASSENGERS_NUMBER_NAME, SwingConstants.RIGHT);
        SpinnerNumberModel spinnerNumberModelPassengersNumber =
                new SpinnerNumberModel(properties.getPassengersNumber(), PASSENGERS_NUMBER_MIN,
                        PASSENGERS_NUMBER_MAX, PASSENGERS_NUMBER_STEP);
        jSpinnerPassengersNumber = new JSpinner(spinnerNumberModelPassengersNumber);
        gridBagConstraints.gridx = UIDimensions.PASSENGERS_NUMBER_LABEL_X;
        gridBagConstraints.gridy = UIDimensions.PASSENGERS_NUMBER_Y;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagLayout.setConstraints(jLabelPassengersNumber, gridBagConstraints);
        gridBagConstraints.gridx = UIDimensions.PASSENGERS_NUMBER_X;
        gridBagConstraints.gridy = UIDimensions.PASSENGERS_NUMBER_Y;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(jSpinnerPassengersNumber, gridBagConstraints);
        add(jLabelPassengersNumber);
        add(jSpinnerPassengersNumber);

        jSliderAnimationBoost = new JSlider(ANIMATION_BOOST_MIN, ANIMATION_BOOST_MAX,
                properties.getAnimationBoost());
        jSliderAnimationBoost.setMajorTickSpacing(UIDimensions.ANIMATION_BOOST_MAJOR_TICK_SPACING);
        jSliderAnimationBoost.setMinorTickSpacing(UIDimensions.ANIMATION_BOOST_MINOR_TICK_SPACING);
        jSliderAnimationBoost.setLabelTable(jSliderAnimationBoost.createStandardLabels(UIDimensions.ANIMATION_BOOST_MAJOR_TICK_SPACING));
        jSliderAnimationBoost.setPaintTicks(true);
        jSliderAnimationBoost.setPaintLabels(true);
        jLabelAnimationBoost = new JLabel(ANIMATION_BOOST_NAME + jSliderAnimationBoost.getValue());
        jSliderAnimationBoost.addChangeListener(this);
        gridBagConstraints.gridx = UIDimensions.ANIMATION_BOOST_X;
        gridBagConstraints.gridy = UIDimensions.ANIMATION_BOOST_Y;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = UIDimensions.ANIMATION_BOOST_PADDING;
        gridBagLayout.setConstraints(jSliderAnimationBoost, gridBagConstraints);
        gridBagConstraints.gridx = UIDimensions.ANIMATION_BOOST_X;
        gridBagConstraints.gridy = UIDimensions.ANIMATION_BOOST_LABEL_Y;
        gridBagConstraints.insets = UIDimensions.ANIMATION_BOOST_LABEL_PADDING;
        gridBagLayout.setConstraints(jLabelAnimationBoost, gridBagConstraints);
        add(jSliderAnimationBoost);
        add(jLabelAnimationBoost);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // TODO Auto-generated method stub
        jLabelAnimationBoost.setText(ANIMATION_BOOST_NAME + jSliderAnimationBoost.getValue());
    }

    public Properties getProperties(){
        int storiesNumber = Integer.parseInt(jSpinnerStoriesNumber.getValue().toString());
        int elevatorCapacity = Integer.parseInt(jSpinnerElevatorCapacity.getValue().toString());
        int passengersNumber = Integer.parseInt(jSpinnerPassengersNumber.getValue().toString());
        int animationBoost = jSliderAnimationBoost.getValue();
        return new Properties(storiesNumber, elevatorCapacity, passengersNumber, animationBoost);
    }

    public void disableProperties(){
        jSpinnerStoriesNumber.setEnabled(false);
        jSpinnerElevatorCapacity.setEnabled(false);
        jSpinnerPassengersNumber.setEnabled(false);
        jSliderAnimationBoost.setEnabled(false);
        jLabelAnimationBoost.setEnabled(false);
        setEnabled(false);
    }
}
