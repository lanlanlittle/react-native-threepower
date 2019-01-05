
# react-native-threepower

## Getting started

`$ npm install react-native-threepower --save`

### Mostly automatic installation

`$ react-native link react-native-threepower`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-threepower` and add `RNThreepower.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNThreepower.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.zxx.threepower.RNThreepowerPackage;` to the imports at the top of the file
  - Add `new RNThreepowerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-threepower'
  	project(':react-native-threepower').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-threepower/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-threepower')
  	```


## Usage
```javascript
import RNThreepower from 'react-native-threepower';

// TODO: What to do with the module?
RNThreepower;
```
  